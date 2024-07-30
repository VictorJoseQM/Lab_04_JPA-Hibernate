package Imobiliaria;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");

        System.out.println(factory.getProperties() );

        /*
         * INSTRUÇÕES PARA EXECUÇÃO DOS TESTES:
         *
         * Para garantir que todos os testes sejam executados corretamente, é importante seguir a ordem de criação das entidades,
         * devido às suas dependências. Abaixo, estão listadas as entidades e suas respectivas dependências:
         *
         * 1. Cliente: Crie um cliente primeiro, pois outras entidades dependem dele.
         *    - Exemplo: TesteInserirCliente.java
         *
         * 2. TipoImovel: Crie um tipo de imóvel antes de criar o imóvel.
         *    - Exemplo: TesteInserirTipoImovel.java
         *
         * 3. Imovel: Para criar um imóvel, é necessário já ter um cliente e um tipo de imóvel.
         *    - Exemplo: TesteInserirImovel.java
         *
         * 4. Locacao: Para criar uma locação, é necessário já ter um imóvel.
         *    - Exemplo: TesteInserirLocacao.java
         *
         * 5. Profissional: Crie um profissional, pois o ServiçoImovel depende dele.
         *    - Exemplo: TesteInserirProfissional.java
         *
         * 6. ServicoImovel: Para criar um serviço de imóvel, é necessário já ter um profissional e um imóvel.
         *    - Exemplo: TesteInserirServicoImovel.java
         *
         * 7. Aluguel: Para criar um aluguel, é necessário já ter uma locação.
         *    - Exemplo: TesteInserirAluguel.java
         *
         * Seguindo essa ordem, você garantirá que todas as dependências necessárias estejam presentes para a execução dos testes.
         *
         * */

        factory.close();
    }
}
