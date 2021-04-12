import java.util.ArrayList;

public class Livraria {
    private String nome;
    private ArrayList<Autor> autores;
    private ArrayList<Editora> editoras;
    private ArrayList<Livro> livros;

    public Livraria(String n){
        this.nome = n;
        this.autores = new ArrayList<Autor>();
        this.editoras = new ArrayList<Editora>();
        this.livros = new ArrayList<Livro>();
        
    }

    public String getNome(){return this.nome;}
    public void setNome(String n){this.nome = n;}
    public ArrayList<Autor> getAutores(){return this.autores;}
    public ArrayList<Editora> getEditoras(){return this.editoras;}
    public ArrayList<Livro> getLivros(){return this.livros;}
    
    //CREATE
    public int cadastrarAutor(String n){
        Autor a = new Autor(n);
        autores.add(a);
        return a.getCodigo();
    }

    public int cadastrarEditora(String n){
        Editora e = new Editora(n);
        this.editoras.add(e);
        return e.getCodigo();
    }

    public int cadastrarLivro(String t, int e, ArrayList<Autor> a){
        for(Editora editora : editoras){
            if(editora.getCodigo() == e){
                Livro l = new Livro(t, editora, a);
                this.livros.add(l);
                for(Autor autoresL : a)
                    autoresL.setLivro(l);
                return l.getCodigo();
            }
        }
        return -1;
    }

    //READ
    public String readAutor(int c){
        for(Autor a : autores){
            if(a.getCodigo() == c){
                return a.toString();
            }
        }
        return null;
    }

    public String readEditora(int c){
        for(Editora e : editoras){
            if(e.getCodigo() == c){
                return e.toString();
            }
        }
        return null;
    }

    public String readLivro(int c){
        for(Livro l : livros){
            if(l.getCodigo() == c){
                return l.toString();
            }
        }
        return null;
    }

    //UPDATE
    public boolean atualizaAutor(int c, String n){
        for(Autor a : autores){
            if(a.getCodigo() == c){
                a.setNome(n);
                return true;
            }
        }
        return false;

    }
    public boolean atualizaAutor(int c, boolean add, int codLivro){
        for(Autor a : autores){
            if(a.getCodigo() == c){
                for(Livro livro : livros){
                    if(livro.getCodigo() == codLivro){
                        Livro l;
                        l = livro;
                        if(add){
                            a.setLivro(l);
                            return true;
                        }else{
                            return a.rmLivro(l);
                        }
                    }
                    System.out.print("| ");
                    break;
                }
            }
        }
        return false;
    }

    public boolean atualizaEditora(int c, String n){
        for(Editora e : editoras){
            if(e.getCodigo() == c){
                e.setNome(n);
                return true;
            }
        }
        return false;
    }

    public boolean atualizaLivro(int c, String t){
        for(Livro l : livros){
            if(l.getCodigo() == c){
                l.setTitulo(t);
                return true;
            }
        }
        return false;
    }
    
    public boolean atualizaLivro(int c, Editora e){
        for(Livro l : livros){
            if(l.getCodigo() == c){
                l.setEditora(e);
                return true;
            }
        }
        return false;
    }

    //DELETE
    public boolean deletaAutor(int c){
        for(Autor a : autores){
            if(a.getCodigo() == c){
                for(Livro l : a.getLivros())
                    livros.remove(l);
                autores.remove(a);
                return true;
            }
        }
        return false;
    }

    public boolean deletaEditora(int c){
        for(Editora e : editoras){
            if(e.getCodigo() == c){
                for(Livro l : livros){
                    if(l.getEditora() == e)
                        livros.remove(l);
                }
                editoras.remove(e);
                return true;
            }
        }
        return false;
    }

    public boolean deletaLivro(int c){
        for(Livro l : livros){
            if(l.getCodigo() == c){
                livros.remove(l);
                return true;
            }
        }
        return false;
    }
    public String toString(){
        return(
            "\nNome: " + nome +
            "\nAutores: " + autores.toString() +
            "\nEditoras: " + editoras.toString() +
            "\nLivros: " + livros.toString()
        );
    }
}
