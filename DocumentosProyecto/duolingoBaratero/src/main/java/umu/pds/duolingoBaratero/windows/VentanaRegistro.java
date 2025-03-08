package umu.pds.duolingoBaratero.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame {
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblPerfil;
    private JPasswordField passwordFieldContraseñaOk;
    private JPasswordField passwordFieldContraseña;
    private JTextField textFieldNickName;
    private JTextField textFieldCorreo;
    private JTextField textFieldNombre;

    public VentanaRegistro() {
        setTitle("Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
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
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TemasWindow ventanaNueva = new TemasWindow(new LogInWindow());
                ventanaNueva.setLocation(getLocation());
                ventanaNueva.setVisible(true);
            }
        });
        panelAbajo.add(btnCancelar);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(Color.CYAN);
        panelAbajo.add(btnRegistrar);

        JPanel panelMedio = new JPanel();
        contentPane.add(panelMedio, BorderLayout.CENTER);
        panelMedio.setLayout(new BoxLayout(panelMedio, BoxLayout.Y_AXIS));

        JPanel panelDatos = new JPanel();
        panelMedio.add(panelDatos);
        GridBagLayout gbl_panelDatos = new GridBagLayout();
        gbl_panelDatos.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_panelDatos.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
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
        gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_textFieldNombre.gridx = 2;
        gbc_textFieldNombre.gridy = 1;
        panelDatos.add(textFieldNombre, gbc_textFieldNombre);
        textFieldNombre.setColumns(10);
        
        JLabel lblCorreo = new JLabel("Correo:");
        GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
        gbc_lblCorreo.anchor = GridBagConstraints.EAST;
        gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
        gbc_lblCorreo.gridx = 1;
        gbc_lblCorreo.gridy = 2;
        panelDatos.add(lblCorreo, gbc_lblCorreo);
        
        textFieldCorreo = new JTextField();
        GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
        gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 0);
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
        gbc_textFieldNickName.insets = new Insets(0, 0, 5, 0);
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
        gbc_passwordFieldContraseña.insets = new Insets(0, 0, 5, 0);
        gbc_passwordFieldContraseña.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordFieldContraseña.gridx = 2;
        gbc_passwordFieldContraseña.gridy = 4;
        panelDatos.add(passwordFieldContraseña, gbc_passwordFieldContraseña);
        
        JLabel lblContraseñaOk = new JLabel("Contraseña OK:");
        GridBagConstraints gbc_lblContraseñaOk = new GridBagConstraints();
        gbc_lblContraseñaOk.anchor = GridBagConstraints.EAST;
        gbc_lblContraseñaOk.insets = new Insets(0, 0, 0, 5);
        gbc_lblContraseñaOk.gridx = 1;
        gbc_lblContraseñaOk.gridy = 5;
        panelDatos.add(lblContraseñaOk, gbc_lblContraseñaOk);
        
        passwordFieldContraseñaOk = new JPasswordField();
        GridBagConstraints gbc_passwordFieldContraseñaOk = new GridBagConstraints();
        gbc_passwordFieldContraseñaOk.fill = GridBagConstraints.HORIZONTAL;
        gbc_passwordFieldContraseñaOk.gridx = 2;
        gbc_passwordFieldContraseñaOk.gridy = 5;
        panelDatos.add(passwordFieldContraseñaOk, gbc_passwordFieldContraseñaOk);

        // Aquí viene el resto de tu código de la ventana

        JPanel panelCosas = new JPanel();
        panelMedio.add(panelCosas);

        // Crear un ImageIcon para el perfil predeterminado
        ImageIcon iconPerfil = new ImageIcon(getClass().getResource("/perfil.png"));
        Image imageIcon = iconPerfil.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
        iconPerfil = new ImageIcon(imageIcon);

        // Crear un botón para seleccionar la foto
        JButton btnPerfil = new JButton("Elige una foto:");
        btnPerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Crear una instancia de JFileChooser
                JFileChooser fileChooser = new JFileChooser();

                // Crear un filtro para solo mostrar imágenes
                fileChooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        // Aceptar directorios y archivos de imagen
                        if (f.isDirectory()) {
                            return true;
                        }
                        String extension = getFileExtension(f);
                        return extension != null && (extension.equals("jpg") || extension.equals("png") || extension.equals("gif") || extension.equals("bmp"));
                    }

                    @Override
                    public String getDescription() {
                        // Descripción del filtro
                        return "Archivos de Imagen (*.jpg, *.png, *.gif, *.bmp)";
                    }
                });

                // Abrir el explorador de archivos
                int opcion = fileChooser.showOpenDialog(VentanaRegistro.this);

                // Verificar si se seleccionó un archivo
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    File archivoSeleccionado = fileChooser.getSelectedFile();

                    // Crear un ImageIcon con la imagen seleccionada
                    ImageIcon nuevoIcon = new ImageIcon(archivoSeleccionado.getAbsolutePath());
                    Image imageIcon = nuevoIcon.getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
                    nuevoIcon = new ImageIcon(imageIcon);

                    // Actualizar el JLabel con la nueva imagen
                    lblPerfil.setIcon(nuevoIcon);
                }
            }

            // Método para obtener la extensión de un archivo
            private String getFileExtension(File f) {
                String nombreArchivo = f.getName();
                int puntoIndice = nombreArchivo.lastIndexOf(".");
                if (puntoIndice > 0) {
                    return nombreArchivo.substring(puntoIndice + 1).toLowerCase();
                }
                return null;
            }
        });
        panelCosas.add(btnPerfil);

        // Inicializar lblPerfil
        lblPerfil = new JLabel(iconPerfil);
        panelCosas.add(lblPerfil);

        JMenuBar menuBar = new JMenuBar();
        panelCosas.add(menuBar);

        JMenu mnSelectCursos = new JMenu("Selecciona un máximo de 3 cursos");
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
}
