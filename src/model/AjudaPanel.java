package model;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class AjudaPanel extends JPanel {

	private static final long serialVersionUID = 5574010412516887329L;

	public AjudaPanel() {
		setLayout(new BorderLayout());

		// JTextPane para suportar formatação
		JTextPane textoAjuda = new JTextPane();
		textoAjuda.setEditable(false);
		textoAjuda.setBackground(Color.WHITE);

		// Definir o conteúdo com formatação
		setConteudoFormatado(textoAjuda);

		// Adicionar à área de scroll
		JScrollPane scrollPane = new JScrollPane(textoAjuda);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		add(scrollPane, BorderLayout.CENTER);
	}

	private void setConteudoFormatado(JTextPane textPane) {
		StyledDocument doc = textPane.getStyledDocument();

		// Estilos
		Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setFontFamily(defaultStyle, "Arial");
		StyleConstants.setFontSize(defaultStyle, 14);

		// Estilo para título principal
		Style titleStyle = doc.addStyle("title", defaultStyle);
		StyleConstants.setBold(titleStyle, true);
		StyleConstants.setFontSize(titleStyle, 18);
		StyleConstants.setForeground(titleStyle, Color.BLUE);

		// Estilo para subtítulos
		Style subtitleStyle = doc.addStyle("subtitle", defaultStyle);
		StyleConstants.setBold(subtitleStyle, true);
		StyleConstants.setFontSize(subtitleStyle, 16);
		StyleConstants.setForeground(subtitleStyle, Color.DARK_GRAY);

		// Estilo para texto em negrito
		Style boldStyle = doc.addStyle("bold", defaultStyle);
		StyleConstants.setBold(boldStyle, true);

		// Estilo para lista
		Style listStyle = doc.addStyle("list", defaultStyle);
		StyleConstants.setLeftIndent(listStyle, 20);

		try {
			// OBJETIVO DO JOGO
			doc.insertString(doc.getLength(), "OBJETIVO DO JOGO\n", titleStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);
			doc.insertString(doc.getLength(), "Descubra todas as casas seguras sem detonar nenhuma mina!\n", boldStyle);
			doc.insertString(doc.getLength(),
					"- O tabuleiro contém minas escondidas e números que indicam quantas minas existem nas casas vizinhas.\n",
					listStyle);
			doc.insertString(doc.getLength(), "\n\n", defaultStyle);

			// COMO JOGAR
			doc.insertString(doc.getLength(), "COMO JOGAR\n", titleStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);

			// CLIQUE ESQUERDO
			doc.insertString(doc.getLength(), "CLIQUE ESQUERDO\n", subtitleStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);
			doc.insertString(doc.getLength(), "- Em casa vazia: Revela a casa\n", listStyle);
			doc.insertString(doc.getLength(), "- Em casa segura: Pode revelar várias casas de uma vez\n", listStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);

			// CLIQUE DIREITO
			doc.insertString(doc.getLength(), "CLIQUE DIREITO\n", subtitleStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);
			doc.insertString(doc.getLength(), "- Primeiro clique: Marca com \"!\" - indica mina suspeita\n", listStyle);
			doc.insertString(doc.getLength(), "- Terceiro clique: Remove marcação\n", listStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);

			// ENTENDA OS NÚMEROS
			doc.insertString(doc.getLength(), "ENTENDA OS NÚMEROS\n", subtitleStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);
			doc.insertString(doc.getLength(), "- 1: Há 1 mina nas 8 casas ao redor\n", listStyle);
			doc.insertString(doc.getLength(), "- 2: Há 2 minas nas casas vizinhas\n", listStyle);
			doc.insertString(doc.getLength(), "- E assim por diante...\n", listStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);

			// CONTADORES
			doc.insertString(doc.getLength(), "CONTADORES\n", subtitleStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);
			doc.insertString(doc.getLength(), "Minas (Esquerdo): ", boldStyle);
			doc.insertString(doc.getLength(), "Quantas minas faltam marcar\n", defaultStyle);
			doc.insertString(doc.getLength(), "Marcadas (Direito): ", boldStyle);
			doc.insertString(doc.getLength(), "Quantas minas estão marcadas\n", defaultStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);

			// COMECE PELOS CANTOS
			doc.insertString(doc.getLength(), "COMECE PELOS CANTOS\n", subtitleStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);
			doc.insertString(doc.getLength(), "- As bordas e cantos têm menos vizinhos, facilitando a dedução\n",
					listStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);

			// USE A LÓGICA
			doc.insertString(doc.getLength(), "USE A LÓGICA\n", subtitleStyle);
			doc.insertString(doc.getLength(), "\n", defaultStyle);
			doc.insertString(doc.getLength(),
					"- Se um \"1\" tem apenas uma casa não revelada ao redor, essa casa é uma mina!\n", listStyle);
			doc.insertString(doc.getLength(),
					"- Se o número na casa é igual à quantidade de casas não reveladas ao redor, todas são minas\n",
					listStyle);

		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	// Método para testar o panel
	// public static void main(String[] args) {
	// JFrame frame = new JFrame("Ajuda - Campo Minado");
	// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setSize(500, 400);
	// frame.setLocationRelativeTo(null);

	// AjudaPanel ajudaPanel = new AjudaPanel();
	// frame.add(ajudaPanel);

	// frame.setVisible(true);
	// }
}