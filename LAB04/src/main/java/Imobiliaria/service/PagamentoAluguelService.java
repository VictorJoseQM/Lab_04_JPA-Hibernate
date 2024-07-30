package Imobiliaria.service;

import Imobiliaria.entity.Aluguel;
import Imobiliaria.repository.AluguelRepository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PagamentoAluguelService {

    private final EntityManager em;
    private final AluguelRepository repositorio;

    public PagamentoAluguelService(EntityManager em) {
        this.em = em;
        this.repositorio = new AluguelRepository(em);
    }

    public void registrarPagamento(Aluguel aluguel, LocalDate dataPagamento, BigDecimal valorPago) {
        aluguel.setDataPagamento(dataPagamento);
        aluguel.setValorPago(valorPago);
        repositorio.salvaOuAtualiza(aluguel);
    }

    public BigDecimal calcularValorComMulta(Aluguel aluguel, LocalDate dataPagamento) {
        LocalDate dataVencimento = aluguel.getDataVencimento();
        BigDecimal valorAluguel = aluguel.getLocacao().getValorAluguel();

        if (!dataPagamento.isAfter(dataVencimento)) {
            return valorAluguel;
        }

        long diasAtraso = ChronoUnit.DAYS.between(dataVencimento, dataPagamento);
        BigDecimal multaDiaria = valorAluguel.multiply(new BigDecimal("0.0033"));
        BigDecimal valorMulta = multaDiaria.multiply(new BigDecimal(diasAtraso));

        BigDecimal valorMaximoMulta = valorAluguel.multiply(new BigDecimal("0.20"));
        if (valorMulta.compareTo(valorMaximoMulta) > 0) {
            valorMulta = valorMaximoMulta;
        }

        return valorAluguel.add(valorMulta);
    }
}
