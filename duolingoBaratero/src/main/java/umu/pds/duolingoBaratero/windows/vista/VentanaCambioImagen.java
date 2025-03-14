package umu.pds.duolingoBaratero.windows.vista;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class VentanaCambioImagen extends JFrame {

	private static final long serialVersionUID = 1L;
	private VentanaCambiaImagenes v;
	private JTextField textField;

	public VentanaCambioImagen(VentanaCambiaImagenes v) {

		this.setBounds(100, 100, 700, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// cierra la ventana cuando cancelas

		this.v = v;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 20, 40, 10, 0, 10, 40, 20, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 0, 0, 0, 30, 0, 0, 30, 0, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel_1 = new JLabel(
				"Si quieres cambiar tu imagen por URL, escribe el URL en el campo de abajo y pulsa el boton");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton btnNewButton_2 = new JButton("URL");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 3;
		btnNewButton_2.addActionListener(e -> cambiarImagenConURL());
		getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 3;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Si quieres cambiar la imagen por una en tus archivos pulsa el boton de abajo");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 5;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		JButton btnNewButton_1 = new JButton("Archivos");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 6;
		btnNewButton_1.addActionListener(e -> cambiarImagen());
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton = new JButton("Volver");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 8;
		btnNewButton.addActionListener(e -> dispose());
		getContentPane().add(btnNewButton, gbc_btnNewButton);
	}

	private void cambiarImagen() {
		// Abrir diálogo para seleccionar archivo
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Seleccionar Imagen");
		fileChooser.setFileFilter(
				new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "png", "jpg", "jpeg", "gif"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// Validar si el archivo seleccionado es una imagen
			if (!selectedFile.getName().toLowerCase().matches(".*\\.(png|jpg|jpeg|gif)$")) {
				JOptionPane.showMessageDialog(this, "Seleccione un archivo de imagen válido.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				BufferedImage originalImage = ImageIO.read(selectedFile);
				String telefono = v.getName();
				File destinationFile = new File("src/main/resources/imagenPerfil" + telefono + ".png");
				v.setDestinationFile(destinationFile);
				ImageIO.write(originalImage, "png", destinationFile);
				v.setIcon(null, null);
				JOptionPane.showMessageDialog(this, "Imagen cambiada y guardada correctamente");
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Error al procesar la imagen: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			dispose();
		}
	}

	@SuppressWarnings("deprecation")
	private void cambiarImagenConURL() {
		try {
			URL imageURL = new URL(textField.getText());
			BufferedImage image = ImageIO.read(imageURL);
			ImageIcon imageIcon = new ImageIcon(image); // Tamaño ajustado
			v.setIcon(imageIcon, imageURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dispose();
	}

}