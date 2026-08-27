package vista;

import modelo.FuncionPelicula;
import modelo.ProductoDulceria;
import modelo.TipoFormato;
import modelo.TipoProducto;
import servicio.CineServicio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Optional;

/**
 * Ventana principal de NovaCinema.
 * Contiene dos pestañas:
 *  - Cartelera: registra funciones de películas y vende boletos.
 *  - Dulcería: registra y vende productos (snacks, bebidas y combos).
 */
public class VentanaCine extends JFrame {

    private final CineServicio cineServicio = new CineServicio();

    // -------- Cartelera --------
    private final JTextField txtCodigo = new JTextField();
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtDuracion = new JTextField();
    private final JTextField txtSala = new JTextField();
    private final JTextField txtCapacidad = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JComboBox<TipoFormato> cbxFormato = new JComboBox<>(TipoFormato.values());
    private final JTextArea txaCartelera = new JTextArea();

    private JButton btnRegistrarPeli;
    private JButton btnVenderBoleto;
    private JButton btnMostrarFunciones;
    private JButton btnPromoEstudiante;

    // -------- Dulcería --------
    private final JTextField txtCodDulceria = new JTextField();
    private final JTextField txtNomDulceria = new JTextField();
    private final JTextField txtPrecioDulceria = new JTextField();
    private final JComboBox<TipoProducto> cbxTipoDulceria = new JComboBox<>(TipoProducto.values());
    private final JTextArea txaDulceria = new JTextArea();

    private JButton btnRegistrarDulceria;
    private JButton btnVenderDulceria;
    private JButton btnPromoDulceria;
    private JButton btnMostrarCatalogoDulceria;

    public VentanaCine() {
        setTitle("NovaCinema - Gestión de Cine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        setContentPane(construirPanelPrincipal());
        configurarEventos();
    }

    private JPanel construirPanelPrincipal() {
        JPanel raiz = new JPanel(new BorderLayout(10, 10));
        raiz.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("NovaCinema - Taquilla, Cartelera y Dulcería");
        titulo.setFont(new Font("Arial Black", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        raiz.add(titulo, BorderLayout.NORTH);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Cartelera", construirTabCartelera());
        pestanas.addTab("Dulcería", construirTabDulceria());
        raiz.add(pestanas, BorderLayout.CENTER);

        return raiz;
    }

    private JPanel construirTabCartelera() {
        JPanel tab = new JPanel(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(new TitledBorder("Datos de la Función"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarFila(formulario, gbc, 0, "Código", txtCodigo);
        agregarFila(formulario, gbc, 1, "Título", txtTitulo);
        agregarFila(formulario, gbc, 2, "Formato", cbxFormato);
        agregarFila(formulario, gbc, 3, "Duración (min)", txtDuracion);
        agregarFila(formulario, gbc, 4, "Sala", txtSala);
        agregarFila(formulario, gbc, 5, "Capacidad", txtCapacidad);
        agregarFila(formulario, gbc, 6, "Precio base", txtPrecio);

        btnRegistrarPeli = new JButton("Registrar Función");
        btnVenderBoleto = new JButton("Vender Boleto");
        btnMostrarFunciones = new JButton("Mostrar Funciones");
        btnPromoEstudiante = new JButton("Promo Estudiante");

        JPanel botones = new JPanel(new GridLayout(2, 2, 6, 6));
        botones.add(btnRegistrarPeli);
        botones.add(btnVenderBoleto);
        botones.add(btnMostrarFunciones);
        botones.add(btnPromoEstudiante);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formulario.add(botones, gbc);

        tab.add(formulario, BorderLayout.WEST);

        txaCartelera.setEditable(false);
        txaCartelera.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        tab.add(new JScrollPane(txaCartelera), BorderLayout.CENTER);

        return tab;
    }
    private JPanel construirTabDulceria() {
        JPanel tab = new JPanel(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(new TitledBorder("Datos del Producto"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarFila(formulario, gbc, 0, "Código", txtCodDulceria);
        agregarFila(formulario, gbc, 1, "Nombre", txtNomDulceria);
        agregarFila(formulario, gbc, 2, "Tipo", cbxTipoDulceria);
        agregarFila(formulario, gbc, 3, "Precio", txtPrecioDulceria);

        btnRegistrarDulceria = new JButton("Registrar Producto");
        btnVenderDulceria = new JButton("Vender Producto");
        btnMostrarCatalogoDulceria = new JButton("Mostrar Catálogo");
        btnPromoDulceria = new JButton("Promo Dulcería");

        JPanel botones = new JPanel(new GridLayout(2, 2, 6, 6));
        botones.add(btnRegistrarDulceria);
        botones.add(btnVenderDulceria);
        botones.add(btnMostrarCatalogoDulceria);
        botones.add(btnPromoDulceria);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formulario.add(botones, gbc);

        tab.add(formulario, BorderLayout.WEST);

        txaDulceria.setEditable(false);
        txaDulceria.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        tab.add(new JScrollPane(txaDulceria), BorderLayout.CENTER);

        return tab;
    }

    /** Agrega una fila "etiqueta + campo" al formulario. */
    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila,
                             String etiqueta, JComponent campo) {
        gbc.gridy = fila;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        panel.add(new JLabel(etiqueta + ":"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(campo, gbc);
    }
    // ============================================================
    // EVENTOS
    // ============================================================

    private void configurarEventos() {

        // 1. REGISTRAR FUNCIÓN
        btnRegistrarPeli.addActionListener(e -> {
            try {
                String codigo = txtCodigo.getText().trim();
                String titulo = txtTitulo.getText().trim();
                int duracion = Integer.parseInt(txtDuracion.getText().trim());
                int sala = Integer.parseInt(txtSala.getText().trim());
                int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                TipoFormato formato = (TipoFormato) cbxFormato.getSelectedItem();

                if (codigo.isEmpty() || titulo.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Debe ingresar el código y el título.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Orden del constructor:
                // (codigo, nombre, categoria, precioBase, capacidadTotal, duracionMinutos, sala)
                FuncionPelicula pelicula = new FuncionPelicula(
                        codigo, titulo, formato, precio, capacidad, duracion, sala);

                cineServicio.registrarPelicula(pelicula);

                actualizarCartelera();
                limpiarCamposCartelera();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Revise los campos numéricos.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 2. VENDER BOLETO
        btnVenderBoleto.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this,
                    "Ingrese el código de la función:");

            if (codigo == null || codigo.isBlank()) {
                return;
            }

            Optional<FuncionPelicula> resultado =
                    cineServicio.buscarPelicula(codigo.trim());

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró una función con el código: " + codigo,
                        "Sin resultados", JOptionPane.WARNING_MESSAGE);
                return;
            }

            FuncionPelicula pelicula = resultado.get();

            String textoCantidad = JOptionPane.showInputDialog(this,
                    "Película: " + pelicula.getNombre()
                            + "\nButacas disponibles: " + pelicula.getAsientosDisponibles()
                            + "\n\nCantidad de boletos:");

            if (textoCantidad == null || textoCantidad.isBlank()) {
                return;
            }

            try {
                int cantidad = Integer.parseInt(textoCantidad.trim());
                Double total = cineServicio.venderBoletos(codigo.trim(), cantidad);

                if (total == null) {
                    JOptionPane.showMessageDialog(this,
                            "No se pudo realizar la venta\n"
                                    + "Verifique la cantidad y las butacas disponibles.",
                            "Venta no realizada", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(this,
                        String.format("Venta realizada correctamente.\n\n"
                                        + "Película: %s\n\nBoletos: %d\nTotal: $%.2f",
                                pelicula.getNombre(), cantidad, total),
                        "Taquilla", JOptionPane.INFORMATION_MESSAGE);

                actualizarCartelera();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Debe ingresar una cantidad válida",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // 3. MOSTRAR FUNCIONES
        btnMostrarFunciones.addActionListener(e -> actualizarCartelera());

        // 4. PROMO ESTUDIANTE (20 % de descuento)
        btnPromoEstudiante.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this,
                    "Ingrese el código de la función para el descuento:");

            if (codigo == null || codigo.isBlank()) {
                return;
            }

            Optional<FuncionPelicula> resultado =
                    cineServicio.buscarPelicula(codigo.trim());

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró una función con el código: " + codigo,
                        "Sin resultados", JOptionPane.WARNING_MESSAGE);
                return;
            }

            FuncionPelicula pelicula = resultado.get();
            double total = cineServicio.aplicarDescuentoPelicula(pelicula);

            JOptionPane.showMessageDialog(this,
                    String.format("Promo Estudiante (20%% de descuento)\n\n"
                                    + "Película: %s\nPrecio normal: $%.2f\nPrecio con descuento: $%.2f",
                            pelicula.getNombre(), pelicula.calcularPrecio(), total),
                    "Promo Estudiante", JOptionPane.INFORMATION_MESSAGE);
        });

        // 5. REGISTRAR PRODUCTO DE DULCERÍA
        btnRegistrarDulceria.addActionListener(e -> {
            try {
                String codigo = txtCodDulceria.getText().trim();
                String nombre = txtNomDulceria.getText().trim();
                double precio = Double.parseDouble(txtPrecioDulceria.getText().trim());
                TipoProducto tipo = (TipoProducto) cbxTipoDulceria.getSelectedItem();

                if (codigo.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Debe ingresar el código y el nombre del producto.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ProductoDulceria producto = new ProductoDulceria(codigo, nombre, tipo, precio);

                cineServicio.registrarProducto(producto);

                actualizarDulceria();
                limpiarCamposDulceria();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese un precio válido",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // 6. VENDER PRODUCTO DE DULCERÍA
        btnVenderDulceria.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this,
                    "Ingrese el código del producto o combo");

            if (codigo == null || codigo.isBlank()) {
                return;
            }

            Optional<ProductoDulceria> resultado =
                    cineServicio.buscarProducto(codigo.trim());

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró un producto con el código: " + codigo,
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ProductoDulceria producto = resultado.get();

            String textoCantidad = JOptionPane.showInputDialog(this,
                    "Producto: " + producto.getNombre()
                            + "\nTipo: " + producto.getCategoria()
                            + "\nPrecio: " + String.format("%.2f", producto.calcularPrecio())
                            + "\nCantidad:");

            if (textoCantidad == null || textoCantidad.isBlank()) {
                return;
            }

            try {
                int cantidad = Integer.parseInt(textoCantidad.trim());
                Double total = cineServicio.venderProducto(codigo.trim(), cantidad);

                if (total == null) {
                    JOptionPane.showMessageDialog(this,
                            "Cantidad no válida para el producto " + codigo,
                            "Dulcería", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(this,
                        String.format("Venta realizada\n\nProducto: %s\n"
                                        + "Cantidad: %d\nTotal: $%.2f",
                                producto.getNombre(), cantidad, total),
                        "Dulcería", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese una cantidad válida",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 7. MOSTRAR CATÁLOGO DE DULCERÍA
        btnMostrarCatalogoDulceria.addActionListener(e -> actualizarDulceria());

        // 8. PROMO DULCERÍA (10 % de descuento)
        btnPromoDulceria.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this,
                    "Ingrese el código del producto para el descuento:");

            if (codigo == null || codigo.isBlank()) {
                return;
            }

            Optional<ProductoDulceria> resultado =
                    cineServicio.buscarProducto(codigo.trim());

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se encontró un producto con el código: " + codigo,
                        "Sin resultados", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ProductoDulceria producto = resultado.get();
            double total = cineServicio.aplicarDescuentoDulceria(producto);

            JOptionPane.showMessageDialog(this,
                    String.format("Promo Dulcería (10%% de descuento)\n\n"
                                    + "Producto: %s\nPrecio normal: $%.2f\nPrecio con descuento: $%.2f",
                            producto.getNombre(), producto.calcularPrecio(), total),
                    "Promo Dulcería", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    // ============================================================
    // ACTUALIZACIÓN DE PANTALLA
    // ============================================================

    private void actualizarCartelera() {
        StringBuilder sb = new StringBuilder("===== CARTELERA =====\n\n");

        if (cineServicio.obtenerCartelera().isEmpty()) {
            sb.append("(Aún no hay funciones registradas)");
        } else {
            for (FuncionPelicula pelicula : cineServicio.obtenerCartelera()) {
                sb.append(pelicula.getDetalle()).append("\n\n");
            }
        }

        txaCartelera.setText(sb.toString());
    }

    private void limpiarCamposCartelera() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtDuracion.setText("");
        txtSala.setText("");
        txtCapacidad.setText("");
        txtPrecio.setText("");
        cbxFormato.setSelectedIndex(0);
    }

    private void actualizarDulceria() {
        StringBuilder sb = new StringBuilder("===== CATÁLOGO DE DULCERÍA =====\n\n");

        if (cineServicio.obtenerDulceria().isEmpty()) {
            sb.append("(Aún no hay productos registrados)");
        } else {
            for (ProductoDulceria producto : cineServicio.obtenerDulceria()) {
                sb.append(producto.getDetalle()).append("\n\n");
            }
        }

        txaDulceria.setText(sb.toString());
    }

    private void limpiarCamposDulceria() {
        txtCodDulceria.setText("");
        txtNomDulceria.setText("");
        txtPrecioDulceria.setText("");
        cbxTipoDulceria.setSelectedIndex(0);
    }
}




