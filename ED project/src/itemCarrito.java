public class itemCarrito {
    prenda prenda;
    int cantidad;
    public itemCarrito(prenda prenda, int cantidad){
        this.cantidad=cantidad;
        this.prenda = prenda;
    }
    public prenda getPrenda() {
        return prenda;
    }
    public String toString() {
        return "Prenda: " + prenda.toString() + ", Cantidad: " + cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
