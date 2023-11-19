public class Empleado {
    private String nombre;
    private String cedula;
    private int sueldo;

    public Empleado(String nombre, String cedula, int sueldo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public double calcularSeguroSocial() {
        return sueldo * (9.35 / 100);
    }

    public int calcularImpuestoRenta() {
        double Impuesto1 = calcularSeguroSocial();
        double sueldoMensual = sueldo-Impuesto1;
        double sueldoAnual = sueldoMensual*12;
        if (sueldoAnual >= 5000 && sueldoAnual <= 10000) {
            return (int) (sueldoAnual * 0.1);
        } else if (sueldoAnual > 10000 && sueldoAnual <= 18000) {
            return (int) (sueldoAnual * 0.2);
        } else if (sueldoAnual > 18000) {
            return (int) (sueldoAnual * 0.3);
        } else {
            return 0;
        }
    }
    @Override
    public String toString() {
        double Impuesto1 = calcularSeguroSocial();
        double Impuesto2 = calcularImpuestoRenta();
        double sueldoMensual = sueldo-Impuesto1;
        double sueldoAnual = sueldoMensual*12;
        return "Empleado " +
                "Nombre: " + nombre +
                ", Cedula: " + cedula +
                ", Sueldo: " + sueldo+
                ", Aporte al Seguro Social: " + Impuesto1 +
                ", Sueldo con Seguro Social: " +sueldoMensual+
                ", Impuesto a la Renta:  " +Impuesto2+
                ", Sueldo Anual: " + (sueldoAnual-Impuesto2);
    }
}