import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class Empresa {
    private LinkedList<Empleado> empleados;
    private Set<String> cedulas;
    public Empresa() {
        empleados = new LinkedList<>();
        cedulas = new HashSet<>();
    }
    public LinkedList<Empleado> getEmpleados() {
        return empleados;
    }

    public Set<String> getCedulas() {
        return cedulas;
    }
    public void encolar(Empleado empleado) {
        empleados.add(empleado);
    }
    public void eliminarEmpleado(Empleado empleado) {
        if (empleados.contains(empleado)) {
            empleados.remove(empleado);
        } else {
            throw new IllegalArgumentException("El empleado no existe");
        }
    }
}
