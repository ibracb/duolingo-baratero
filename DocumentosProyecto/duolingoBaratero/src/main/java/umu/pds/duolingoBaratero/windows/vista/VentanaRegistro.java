package umu.pds.duolingoBaratero.windows.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import umu.pds.duolingoBaratero.controllers.ControladorDuolingoBaratero;
import umu.pds.duolingoBaratero.controllers.ControladorUsuario;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.Box;
import java.awt.Dimension;

public class VentanaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
	private static final File IMAGEN_POR_DEFECTO = null;
	private static final int DEFAUL_HEIGHT_AND_WIDTH = 75;

    private JPanel contentPane;
    private JLabel lblPerfil;
    private JPasswordField passwordFieldContraseñaOk;
    private JPasswordField passwordFieldContraseña;
    private JTextField textFieldNickName;
    private JTextField textFieldCorreo;
    private JTextField textFieldNombre;
    
    private VentanaCursos v;
    private JLabel lblCorreo;
	private URL url;
	
	private File destinationFile = IMAGEN_POR_DEFECTO;


    public VentanaRegistro(VentanaCursos v) {
    	this.v = v;
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(450, 325);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelArriba = new JPanel();
        panelArriba.setBackground(new Color(200, 220, 255));
        contentPane.add(panelArriba, BorderLayout.NORTH);

        JLabel lblTexto = new JLabel("⚡ ¡Regístrate! 🚀¡Sé la diferencia!⭐️");
        panelArriba.add(lblTexto);

        JPanel panelAbajo = new JPanel();
        contentPane.add(panelAbajo, BorderLayout.SOUTH);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> closeWindow());
        panelAbajo.add(btnCancelar);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> manejarRegistro());
        panelAbajo.add(btnRegistrar);

        JPanel panelMedio = new JPanel();
        contentPane.add(panelMedio, BorderLayout.CENTER);
        panelMedio.setLayout(new BoxLayout(panelMedio, BoxLayout.Y_AXIS));

        JPanel panelDatos = new JPanel();
        panelMedio.add(panelDatos);
        GridBagLayout gbl_panelDatos = new GridBagLayout();
        gbl_panelDatos.columnWidths = new int[]{20, 0, 0, 20, 0};
        gbl_panelDatos.rowHeights = new int[]{10, 0, 0, 0, 0, 0, 0};
        gbl_panelDatos.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panelDatos.setLayout(gbl_panelDatos);
        
        JLabel lblNombre = new JLabel("Nombre:");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.EAST;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 1;
        gbc_lblNombre.gridy = 1;
        panelDatos.add(lblNombre, gbc_lblNombre);
        
        textFieldNombre = new JTextField();
        GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.gridx = 2;
        gbc_textFieldNombre.gridy = 1;
        panelDatos.add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);
        
        lblCorreo = new JLabel("Correo:");
        GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
        gbc_lblCorreo.anchor = GridBagConstraints.EAST;
        gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
        gbc_lblCorreo.gridx = 1;
        gbc_lblCorreo.gridy = 2;
        panelDatos.add(lblCorreo, gbc_lblCorreo);
        
        textFieldCorreo = new JTextField();
        GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
        gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldCorreo.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldCorreo.gridx = 2;
        gbc_textFieldCorreo.gridy = 2;
        panelDatos.add(textFieldCorreo, gbc_textFieldCorreo);
        textFieldCorreo.setColumns(10);
        
        JLabel lblNickName = new JLabel("NickName:");
        GridBagConstraints gbc_lblNickName = new GridBagConstraints();
        gbc_lblNickName.anchor = GridBagConstraints.EAST;
        gbc_lblNickName.insets = new Insets(0, 0, 5, 5);
        gbc_lblNickName.gridx = 1;
        gbc_lblNickName.gridy = 3;
        panelDatos.add(lblNickName, gbc_lblNickName);
        
        textFieldNickName = new JTextField();
        GridBagConstraints gbc_textFieldNickName = new GridBagConstraints();
        gbc_textFieldNickName.insets = new Insets(0, 0, 5, 5);
        gbc_textFieldNickName.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNickName.gridx = 2;
        gbc_textFieldNickName.gridy = 3;
        panelDatos.add(textFieldNickName, gbc_textFieldNickName);
        textFieldNickName.setColumns(10);
        
        JLabel lblContraseña = new JLabel("Contraseña:");
        GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
        gbc_lblContraseña.anchor = GridBagConstraints.EAST;
        gbc_lblContraseña.insets = new Insets(0, 0, 5, 5);
        gbc_lblContraseña.gridx = 1;
        gbc_lblContraseña.gridy = 4;
        panelDatos.add(lblContraseña, gbc_lblContraseña);
        
        passwordFieldContraseña = new JPasswordField();
        GridBagConstraints gbc_passwordFieldContraseña = new GridBagConstraints();
        gbc_passwordFieldContraseña.insets = new Insets(0, 0, 5, 5);
        gbc_passwordFieldContraseña.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordFieldContraseña.gridx = 2;
        gbc_passwordFieldContraseña.gridy = 4;
        panelDatos.add(passwordFieldContraseña, gbc_passwordFieldContraseña);
        
        JLabel lblContraseñaOk = new JLabel("Contraseña:");
        GridBagConstraints gbc_lblContraseñaOk = new GridBagConstraints();
        gbc_lblContraseñaOk.anchor = GridBagConstraints.EAST;
        gbc_lblContraseñaOk.insets = new Insets(0, 0, 0, 5);
        gbc_lblContraseñaOk.gridx = 1;
        gbc_lblContraseñaOk.gridy = 5;
        panelDatos.add(lblContraseñaOk, gbc_lblContraseñaOk);
        
        passwordFieldContraseñaOk = new JPasswordField();
        GridBagConstraints gbc_passwordFieldContraseñaOk = new GridBagConstraints();
        gbc_passwordFieldContraseñaOk.insets = new Insets(0, 0, 0, 5);
        gbc_passwordFieldContraseñaOk.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordFieldContraseñaOk.gridx = 2;
        gbc_passwordFieldContraseñaOk.gridy = 5;
        panelDatos.add(passwordFieldContraseñaOk, gbc_passwordFieldContraseñaOk);

        // Aquí viene el resto de tu código de la ventana

        JPanel panelCosas = new JPanel();
        panelMedio.add(panelCosas);

        // Crear un ImageIcon para el perfil predeterminado
        ImageIcon iconPerfil = new ImageIcon(getClass().getResource("/profile.png"));
        Image imageIcon = iconPerfil.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        iconPerfil = new ImageIcon(imageIcon);

        // Crear un botón para seleccionar la foto
        JButton btnPerfil = new JButton("Elige una foto:");
        btnPerfil.setHorizontalTextPosition(SwingConstants.LEFT);
        btnPerfil.setHorizontalAlignment(SwingConstants.TRAILING);
        btnPerfil.addActionListener(e -> abrirVentanaCambioImagen());
        panelCosas.add(btnPerfil);

        // Inicializar lblPerfil
        lblPerfil = new JLabel(iconPerfil);
        panelCosas.add(lblPerfil);

        JMenuBar menuBar = new JMenuBar();
        panelCosas.add(menuBar);

        JMenu mnSelectCursos = new JMenu("Cursos de Interes");
        menuBar.add(mnSelectCursos);

        JCheckBoxMenuItem mntmIdiomas = new JCheckBoxMenuItem("🗣️ Idiomas");
        mnSelectCursos.add(mntmIdiomas);

        JCheckBoxMenuItem mntmProgramacion = new JCheckBoxMenuItem("💻 Programación");
        mnSelectCursos.add(mntmProgramacion);

        JCheckBoxMenuItem mntmMusica = new JCheckBoxMenuItem("🎶 Música");
        mnSelectCursos.add(mntmMusica);

        JCheckBoxMenuItem mntmCiencia = new JCheckBoxMenuItem("🔬 Ciencia");
        mnSelectCursos.add(mntmCiencia);

        JCheckBoxMenuItem mntmEstudios = new JCheckBoxMenuItem("🎓 Estudios académicos");
        mnSelectCursos.add(mntmEstudios);
    }
    
    private void manejarRegistro() {
		String nombre, nickname, correo, passwd1, passwd2;
		nombre = textFieldNombre.getText();
		nickname = textFieldNickName.getText();
		correo = textFieldCorreo.getText();
		passwd1 = new String(passwordFieldContraseña.getPassword());
		passwd2 = new String(passwordFieldContraseñaOk.getPassword());

	
		if (hasRequiredFields(nombre, nickname, correo, passwd1, passwd2)) {
			JOptionPane.showMessageDialog(null, "Faltan campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (!nombre.matches("[a-zA-Z0-9]+")) {
			JOptionPane.showMessageDialog(null, "El nombre contiene caracteres no permitidos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// No me funciona jope
//		if (!nombre.matches("[a-zA-Z0-9]+@[a-zA-Z0-9.-]+.com")) {
//			JOptionPane.showMessageDialog(null, "El dominio de correo no esta permitido.", "Error",
//					JOptionPane.ERROR_MESSAGE);
//			return;
//		}

		if (!passwd1.equals(passwd2)) {
			JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean result = ControladorUsuario.getInstancia().registrarUsuario(nombre, nickname, correo, passwd1);
		if (!result) {
			JOptionPane.showMessageDialog(null, "Ya estas registrado o ha ocurrido un error", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (destinationFile != null) {
				ControladorUsuario.getInstancia().setImagen(destinationFile.getAbsolutePath());
			} else if (url != null) {
				ControladorUsuario.getInstancia().setImagen(url.toString());

			}


			JOptionPane.showMessageDialog(null, "Sus datos han sido guardados correctamente", "Conseguido",
					JOptionPane.PLAIN_MESSAGE);
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.setVisible(true);
			this.dispose();
		}

	}
    
	private boolean hasRequiredFields(String nombre, String nickname, String correo, String passwd1, String passwd2) {
		return nombre.isEmpty() || nickname.isEmpty() || correo.isEmpty() || passwd1.isEmpty() || passwd2.isEmpty();
	}

    
    private void closeWindow() {
        v.setVisible(true);
        this.dispose();
    }
    
	private void abrirVentanaCambioImagen() {
		String correo = textFieldCorreo.getText();

		// Validar que el teléfono no esté vacío
		if (correo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor ingrese todos los datos antes de cambiar la imagen.",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		VentanaCambioImagen vci = new VentanaCambioImagen(this);
		vci.setVisible(true);
	}

	public void setIcon() {
		String path = destinationFile.getAbsolutePath();
        lblPerfil.setIcon(
        		ControladorUsuario.getInstancia().getScaledImage(new ImageIcon(path), DEFAUL_HEIGHT_AND_WIDTH));
	}

	public void setIcon(ImageIcon imageIcon, URL url) {
		if (url != null) {
			this.url = url;
			imageIcon = new ImageIcon(url);
		} else if (destinationFile != null) {
			String path = destinationFile.getAbsolutePath();
			imageIcon = new ImageIcon(path);
		}
        lblPerfil.setIcon(ControladorUsuario.getInstancia().getScaledImage(imageIcon, DEFAUL_HEIGHT_AND_WIDTH));
	}

	public String getCorreo() {
		return lblCorreo.getText();
	}
	
	public void setDestinationFile(File d) {
		destinationFile = d;
	}


}
