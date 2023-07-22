import java.io.Serializable;

public class Cancion implements Serializable {
    private String titulo;
    private int ano;
    private String genero;

    public Cancion(String titulo, int ano, String genero) {
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", genero='" + genero + '\'' +
                '}';
    }
}
