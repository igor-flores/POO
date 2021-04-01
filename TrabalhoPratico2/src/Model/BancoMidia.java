package Model;

import java.util.ArrayList;

public class BancoMidia {
    private ArrayList<Midia> midias;

    public BancoMidia(){
        midias = new ArrayList<>();
    }

    public void setMidias(ArrayList<Midia> midias) { this.midias = midias; }

    public ArrayList<Midia> getMidias() { return midias; }

    public ArrayList<String> getListGenero(){
        ArrayList<String> generos = new ArrayList<>();

        for (Midia m: midias) {
            if(m instanceof MidiaReproducao)
                generos.add(((MidiaReproducao) m).getGenero());
        }

        return generos;
    }
}
