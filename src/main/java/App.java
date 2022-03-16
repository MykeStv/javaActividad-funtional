import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        //REGISTRO DATA
        Factura f1 = new Factura("ordenador",3, 3500, LocalDate.of(2022, 03, 12));
        Factura f2 = new Factura("movil", 5, 2750, LocalDate.of(2022, 02, 7));
        Factura f3 = new Factura("impresora", 2, 1175, LocalDate.of(2022, 03, 03));
        Factura f4 = new Factura("imac", 3, 5500, LocalDate.now());
        Factura f5 = new Factura("Ipad", 4, 2200, LocalDate.of(2022, 03, 16));


        //Generar lista
        List<Factura> lista = new ArrayList<Factura>();
        //agregando datos
        lista.add(f1);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);
        lista.add(f5);


        //FILTER FUNCTIONS

        //Filter by date before first
        Factura facturaFilterDate1 = lista.stream().filter( item -> {
           return item.getDate().isBefore(LocalDate.of(2022, 03, 01));
        }).findFirst().get();
        System.out.println("\nFILTRO DE LA PRIMERA FACTURA ANTES DE MARZO");
        System.out.println( facturaFilterDate1.getCode() +" " + facturaFilterDate1.getDescription() + " " +  facturaFilterDate1.getDate());

        //Filter by date before first
        List<Factura> facturaFilterDate2 = lista.stream().filter( item -> {
            return item.getDate().isAfter(LocalDate.of(2022, 03, 01));
        }).collect(Collectors.toList());
        System.out.println("\nFILTRO DE FACTURAS DESPUES DE MARZO");
        facturaFilterDate2.forEach(System.out::println);

        //Filter more than cantity
        List<Factura> facturasFilterCantidad = lista.stream().filter( item -> {
            return item.getNumProducts() >= 3;
        }).collect(Collectors.toList());
        System.out.println("\nFILTRO DE FACTURAS CANTIDAD MAYOR O IGUAL");
        facturasFilterCantidad.forEach(System.out::println);

        //Filter less than cantity
        List<Factura> facturasFilterCantidad2 = lista.stream().filter( item -> {
            return item.getNumProducts() < 3;
        }).collect(Collectors.toList());
        System.out.println("\nFILTRO DE FACTURAS CANTIDAD MENOR");
        facturasFilterCantidad2.forEach(System.out::println);

        //Filter equals cantity
        Optional<Factura> facturaFilterDescription = lista.stream().filter(item -> {
            return item.getDescription() == "Imac";
        }).findAny();  //findAny returns optional, so we neet the .get() method
        System.out.println("\nFILTRO DE FACTURAS DESCRIPCION");
        System.out.println(facturaFilterDescription.isEmpty()?"No se encuantra":facturaFilterDescription);

        //Filter by price greater than
        List<Factura> facturasFilterPrice = lista.stream().filter(item -> {
            return item.getPrice() >= 2000;
        }).collect(Collectors.toList());
        System.out.println("\nFILTRO DE FACTURAS PRICE GREATER THAN");
        facturasFilterPrice.forEach(System.out::println);


        //Predicate method
        /*Predicate<Factura> predicado = new Predicate<Factura>() {


            @Override
            public boolean test(Factura factura) {
                System.out.println("p: " + factura.getPrice());
                return factura.getPrice()<300;
            }
        };

        Factura facturaFiltro1 = lista.stream()
                .filter(predicado).findFirst().get();
        System.out.println("FACTURA UNICA: " + facturaFiltro1.getPrice());

        //filtraje con streams
        Factura facturaFiltro2 = lista.stream().filter(element -> {
                    return element.getPrice()>300;
                }).findFirst().get();

        System.out.println(facturaFiltro2.getPrice());*/

    }

}

class Factura {
    private String code;
    private String description;
    private int numProducts;
    private double price;
    private LocalDate date;

    public Factura() {
    }

    public Factura(String description, int numProducts, double price, LocalDate date) {
        this.code = String.valueOf(UUID.randomUUID());
        this.description = description;
        this.numProducts = numProducts;
        this.price = price;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", numProducts=" + numProducts +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
