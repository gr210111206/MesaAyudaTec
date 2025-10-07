package mesaayudatec;

public class Item {
    private final int id;
    private final String nombre;

    public Item(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre; // lo que se muestra en el JComboBox
    }
}
