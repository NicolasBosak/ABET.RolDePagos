import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashSet;

public class Ventana {
    private JTabbedPane tabbedPane1;
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtSueldo;
    private JButton btnIngresar;
    private JList lstListarEmpleados;
    private JPanel Principal;
    private JButton btnListar;
    private JList lstLista2;
    private JComboBox cboCedula;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JTextField txtMNombre;
    private JTextField txtMSueldo;

    private void llenarJlist() {
        dlm.clear();
        for (Empleado empleado : empresa.getEmpleados()) {
            dlm.addElement(empleado);
        }
        lstListarEmpleados.setModel(dlm);
    }
    DefaultListModel dlm=new DefaultListModel();
    private Empresa empresa;

    public Ventana() {
        empresa = new Empresa();
        txtCedula.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (!txtCedula.getText().matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cédula válida");
                    txtCedula.setText("");
                }
            }
        });
        ((AbstractDocument) txtSueldo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                if (text.matches("\\d*")) {
                    fb.insertString(offset, text, attr);
                }
            }
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
                if (text.matches("\\d*")) {
                    fb.replace(offset, length, text, attr);
                }
            }
        });
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String cedula = txtCedula.getText();
                int sueldo = Integer.parseInt(txtSueldo.getText());
                if (empresa.getCedulas().contains(cedula)) {
                    JOptionPane.showMessageDialog(null, "La cédula ya está registrada");
                    return;
                }
                Empleado nuevo = new Empleado(nombre, cedula, sueldo);
                empresa.encolar(nuevo);
                empresa.getCedulas().add(cedula);
                llenarJlist();
                JOptionPane.showMessageDialog(null, "Empleado Ingresado Correctamente");
                txtNombre.setText("");
                txtCedula.setText("");
                txtSueldo.setText("");
                cboCedula.addItem(nuevo.getNombre() + " - " + nuevo.getCedula());
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel dlm=new DefaultListModel();
                for (Empleado empleado : empresa.getEmpleados()) {
                    dlm.addElement(empleado);
                }
                lstLista2.setModel(dlm);
            }
        });
        cboCedula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cboCedula.removeActionListener(this);
            }
        });
        ((AbstractDocument) txtMSueldo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                if (text.matches("\\d*")) {
                    fb.insertString(offset, text, attr);
                }
            }
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
                if (text.matches("\\d*")) {
                    fb.replace(offset, length, text, attr);
                }
            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = (String) cboCedula.getSelectedItem();
                if (cedula != null) {
                    String[] parts = cedula.split(" - ");
                    String selectedCedula = parts[1];
                    Empleado empleadoToModify = null;
                    for (Empleado empleado : empresa.getEmpleados()) {
                        if (empleado.getCedula().equals(selectedCedula)) {
                            empleadoToModify = empleado;
                            break;
                        }
                    }
                    if (empleadoToModify != null) {
                        String nombre = txtMNombre.getText();
                        int sueldo = Integer.parseInt(txtMSueldo.getText());
                        empleadoToModify.setNombre(nombre);
                        empleadoToModify.setSueldo(sueldo);
                        cboCedula.removeAllItems();
                        for (Empleado empleado : empresa.getEmpleados()) {
                            cboCedula.addItem(empleado.getNombre() + " - " + empleado.getCedula());
                        }
                        cboCedula.updateUI();
                        JOptionPane.showMessageDialog(null, "Datos del empleado actualizados correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, seleccione un empleado para modificar");
                    }
                }
                txtMNombre.setText("");
                txtMSueldo.setText("");
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = (String) cboCedula.getSelectedItem();
                if (cedula != null) {
                    String[] parts = cedula.split(" - ");
                    String selectedCedula = parts[1];
                    Empleado empleadoToRemove = null;
                    for (Empleado empleado : empresa.getEmpleados()) {
                        if (empleado.getCedula().equals(selectedCedula)) {
                            empleadoToRemove = empleado;
                            break;
                        }
                    }
                    if (empleadoToRemove != null) {
                        empresa.eliminarEmpleado(empleadoToRemove);
                        cboCedula.removeItem(cedula);
                        JOptionPane.showMessageDialog(null, "Empleado Eliminado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, seleccione un empleado para eliminar");
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
