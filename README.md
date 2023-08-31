RELATÓRIO DA CALCULADORA

1 - Importações:
O código começa importando as classes necessárias do pacote javax.swing e java.awt para construir a interface gráfica da calculadora.

2 - Método main:
O método main inicia a execução do programa, chamando o método createGUI() dentro de um bloco SwingUtilities.invokeLater(). Isso garante que a criação da interface gráfica seja executada na thread de eventos da interface gráfica (Event Dispatch Thread), evitando problemas de concorrência.

3 - Método createGUI():
Este método é responsável por construir a interface gráfica da calculadora.

4 - Configuração do Look and Feel:
O método tenta definir o Look and Feel (aparência) do sistema através do método UIManager.setLookAndFeel(), que é usado para dar à interface gráfica a aparência do sistema operacional em que o programa é executado.

5 - Configuração da Janela:
É criada uma instância de JFrame (a janela da aplicação) com o título "Calculadora no Estilo do Windows 11". O método setDefaultCloseOperation() é chamado para definir o comportamento ao fechar a janela. setSize() define as dimensões da janela, setLayout() define o layout da janela como BorderLayout e setLocationRelativeTo() posiciona a janela no centro da tela.

6 - Campo de Texto:
Um JTextField é criado para exibir as entradas e resultados dos cálculos. Ele é configurado como não editável e com alinhamento à direita. É adicionado à parte superior da janela (NORTH) usando o BorderLayout.

7 - Painel de Botões:
Um JPanel chamado buttonPanel é criado com um layout de grade 4x4 e preenchimento entre os botões. Isso cria o layout da calculadora com 16 botões.

8 - Botões:
Um array de botões JButton é criado, representando os botões da calculadora. Os rótulos dos botões são definidos em um array buttonLabels contendo os dígitos, operadores e botões especiais da calculadora.

9 - Ciclo de Criação dos Botões:
Um loop for é utilizado para criar e configurar cada botão da calculadora. Cada botão é configurado com fonte, cor de fundo e um ouvinte de ação (ActionListener). O ouvinte de ação é implementado inline, respondendo a cliques nos botões.

10 - Lógica de Cálculo:
A lógica de cálculo ocorre dentro do ouvinte de ação dos botões. Quando um botão é clicado, o rótulo do botão é recuperado. Se o rótulo for "C", o campo de texto é limpo. Se for "=", a expressão no campo de texto é avaliada e o resultado é exibido. Caso contrário, o rótulo do botão é adicionado ao campo de texto.

11 - Avaliação de Expressões:
A avaliação das expressões é implementada usando um analisador de expressões simples. A implementação é feita dentro de uma classe anônima. Ela utiliza uma abordagem de análise recursiva para avaliar expressões aritméticas simples contendo números, operadores de adição, subtração, multiplicação e divisão, além de parênteses.

12 - Adição de Componentes:
Os botões são adicionados ao painel de botões (buttonPanel) e o painel é adicionado ao centro da janela.

13 - Exibição da Janela:
Finalmente, a janela é configurada como visível.





