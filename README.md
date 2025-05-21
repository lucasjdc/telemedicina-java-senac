### 2501-Projeto Integrador: Análise de soluções integradas para as organizações 
### Análise e desenvolvimento de sistemas

**Membros:**
- Anna Carolina de Oliveira
- João Victor Teixeira Requena
- José Gabriel Siqueira dos Santos da Silva
- Júlia Oliveira de Melo
- Lucas José Pereira da Costa
- Maryanne Santos Oliveira
- Matheus Santos Leandro
- Renata Dayana de Oliveira Menezes

## Estrutura de Diretórios - MVC (Model-View-Controller)

### Descrição da Estrutura:

- **/br.senac.telemedicina**: Pacote principal do projeto.
  - **/dao**: Data Access Object, responsável por interagir com a base de dados.
  - **/database**: Criação e atualização do banco de dados SQLite.
  - **/model**: Contém as classes e objetos de dados do sistema.
  - **/ui**: Interface de usuário.
    - **/activity**: Arquivos de atividades da interface.
    - **/recyclerview.adapter**: Adaptadores para o RecyclerView, responsável por gerenciar dados e visualização na interface.<br>
- **/res**: Recursos do projeto.
  - **/layout**: Arquivos de layout XML para as telas da interface.

---

## Visão Geral da Arquitetura do Sistema

O sistema de telemedicina foi desenvolvido seguindo a arquitetura **MVC (Model-View-Controller)**, que promove uma separação clara das responsabilidades entre as camadas do sistema:

- **Model**: Representa os dados e as regras de negócio.
- **View**: Responsável pela interface com o usuário.
- **Controller**: Faz a mediação entre as ações do usuário e as respostas do sistema.

Essa abordagem facilita a organização, manutenção e escalabilidade do projeto.

---

## Desenvolvimento do Frontend

O frontend foi desenvolvido utilizando os componentes nativos do Android. A interface foi projetada para ser simples, responsiva e acessível, focando na usabilidade os usuários.

Foram utilizados os seguintes recursos:

- **Activities** para navegação entre telas.
- **RecyclerView** com adaptadores personalizados para listar dados dos pacientes.
- **Layouts XML** organizados na pasta `/res/layout`, garantindo consistência visual.

---

## Desenvolvimento do Backend

O backend é responsável por gerenciar a lógica da aplicação e persistência de dados. Ele foi implementado em Java, com os seguintes componentes:

- **Camada DAO (`/dao`)**: Realiza a comunicação com o banco de dados SQLite, incluindo operações de criação, leitura, atualização e exclusão (CRUD).
- **Banco de dados SQLite**: Configurado no pacote `/database` com as tabelas necessárias para funcionamento do sistema.

Essa estrutura garante o encapsulamento das operações críticas e separação da lógica de negócio da interface.

---

## Modelos de Dados Utilizados

Os modelos de dados estão localizados no pacote `/model` e representam as principais entidades do sistema de telemedicina, como:


- `Paciente`

Cada classe modelo contém atributos correspondentes às colunas do banco de dados e são utilizadas tanto no backend quanto no frontend para exibir ou manipular informações do sistema.

---


## Vídeo de Apresentação

A demonstração do funcionamento do aplicativo Telemedicina está disponível no YouTube:

🔗 [Apresentação do Projeto Telemedicina](https://youtube.com/shorts/yIkcH_h4zR8?feature=share)

Este vídeo mostra as principais funcionalidades da aplicação, com foco na navegação, cadastro de pacientes e histórico médico.

---
