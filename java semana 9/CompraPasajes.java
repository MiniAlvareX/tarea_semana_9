import javax.swing.*;
import java.awt.*;

public class CompraPasajes extends JFrame {

    private JTextField txtNombre, txtDocumento, txtFecha;
    private JCheckBox chkAudifonos, chkManta, chkRevistas;
    private JRadioButton rbPrimerPiso, rbSegundoPiso;
    private JComboBox<String> cmbOrigen, cmbDestino;
    private JList<String> lstServicio;

    public CompraPasajes() {
        setTitle("Compra de Pasajes - Empresa de Transporte");
        setSize(500, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Nombre completo:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Documento de identidad:"));
        txtDocumento = new JTextField();
        panel.add(txtDocumento);

        panel.add(new JLabel("Fecha de viaje:"));
        txtFecha = new JTextField("DD/MM/AAAA");
        panel.add(txtFecha);

        panel.add(new JLabel("Lugar de origen:"));
        cmbOrigen = new JComboBox<>(new String[]{
            "Arequipa", "Lima", "Cusco", "Puno", "Tacna"
        });
        panel.add(cmbOrigen);

        panel.add(new JLabel("Lugar de destino:"));
        cmbDestino = new JComboBox<>(new String[]{
            "Lima", "Arequipa", "Cusco", "Puno", "Tacna"
        });
        panel.add(cmbDestino);

        panel.add(new JLabel("Piso del bus:"));

        JPanel panelPiso = new JPanel();
        rbPrimerPiso = new JRadioButton("1er piso", true);
        rbSegundoPiso = new JRadioButton("2do piso");

        ButtonGroup grupoPiso = new ButtonGroup();
        grupoPiso.add(rbPrimerPiso);
        grupoPiso.add(rbSegundoPiso);

        panelPiso.add(rbPrimerPiso);
        panelPiso.add(rbSegundoPiso);
        panel.add(panelPiso);

        panel.add(new JLabel("Servicios opcionales:"));

        JPanel panelServicios = new JPanel();
        chkAudifonos = new JCheckBox("Audífonos");
        chkManta = new JCheckBox("Manta");
        chkRevistas = new JCheckBox("Revistas");

        panelServicios.setLayout(new GridLayout(3, 1));
        panelServicios.add(chkAudifonos);
        panelServicios.add(chkManta);
        panelServicios.add(chkRevistas);
        panel.add(panelServicios);

        panel.add(new JLabel("Calidad del servicio:"));

        lstServicio = new JList<>(new String[]{
            "Económico", "Standard", "VIP"
        });
        lstServicio.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );
        lstServicio.setSelectedIndex(0);

        panel.add(new JScrollPane(lstServicio));

        JButton btnMostrar = new JButton("Mostrar resumen");
        JButton btnLimpiar = new JButton("Limpiar");

        panel.add(btnMostrar);
        panel.add(btnLimpiar);

        btnMostrar.addActionListener(e -> mostrarResumen());

        btnLimpiar.addActionListener(e -> limpiarCampos());

        add(panel);
        setVisible(true);
    }

    private void mostrarResumen() {

        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        String fecha = txtFecha.getText().trim();

        if (nombre.isEmpty() || documento.isEmpty()
                || fecha.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Complete todos los datos del pasajero.",
                "Advertencia",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String piso = rbPrimerPiso.isSelected()
                ? "Primer piso" : "Segundo piso";

        String servicios = "";

        if (chkAudifonos.isSelected())
            servicios += "Audífonos, ";

        if (chkManta.isSelected())
            servicios += "Manta, ";

        if (chkRevistas.isSelected())
            servicios += "Revistas, ";

        if (servicios.isEmpty())
            servicios = "Ninguno";
        else
            servicios = servicios.substring(
                0, servicios.length() - 2
            );

        String resumen =
            "RESUMEN DE COMPRA DE PASAJE\n\n"
            + "Nombre: " + nombre + "\n"
            + "Documento: " + documento + "\n"
            + "Fecha de viaje: " + fecha + "\n"
            + "Origen: " + cmbOrigen.getSelectedItem() + "\n"
            + "Destino: " + cmbDestino.getSelectedItem() + "\n"
            + "Piso: " + piso + "\n"
            + "Servicios: " + servicios + "\n"
            + "Calidad: " + lstServicio.getSelectedValue();

        JOptionPane.showMessageDialog(
            this,
            resumen,
            "Resumen del pasajero",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void limpiarCampos() {

        txtNombre.setText("");
        txtDocumento.setText("");
        txtFecha.setText("");

        chkAudifonos.setSelected(false);
        chkManta.setSelected(false);
        chkRevistas.setSelected(false);

        rbPrimerPiso.setSelected(true);

        cmbOrigen.setSelectedIndex(0);
        cmbDestino.setSelectedIndex(0);

        lstServicio.setSelectedIndex(0);

        JOptionPane.showMessageDialog(
            this,
            "Todos los campos han sido reiniciados."
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CompraPasajes();
        });
    }
}
