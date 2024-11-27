
import java.io.IOException;
import java.util.Scanner;


import java.io.File;

public class Main {

	//estrutura de hash
    static class HashColisaoExterior<T> {
        private ArvoreBinariaBusca [] vetor;
        private int nElementos;

        @SuppressWarnings("unchecked")
        public HashColisaoExterior(int capacidade) {
            this.vetor = new ArvoreBinariaBusca[capacidade];
            for (int i = 0; i < vetor.length; i++) {
                this.vetor[i] = new ArvoreBinariaBusca();
            }
            this.nElementos = 0;
        }

        private int funcaoHashPorLetraInicial(PalavraChave palavra) {
            char primeiraLetra = palavra.palavra.toLowerCase().charAt(0); // Convertendo para minúscula
            int indice = primeiraLetra - 'a';
            return (indice >= 0 && indice < vetor.length) ? indice : -1;
        }

        public void insere(PalavraChave palavra) {
            int endereco = funcaoHashPorLetraInicial(palavra);
            if (endereco != -1) {
              
          //      System.out.println("Inserindo '" + palavra + "' na linha   no bucket " + (endereco + 1));
                this.vetor[endereco].insere( palavra);
                this.nElementos++;
            } else {
                System.out.println("Palavra fora do intervalo de buckets: " + palavra);
            }
        }

        public void imprimeTabela() {
            for (int i = 0; i < vetor.length; i++) {
                System.out.print("Bucket " + (i + 1) + " -> ");
                vetor[i].imprimeEmOrdem();
                System.out.println();
            }
        }
        
        public PalavraChave busca(String palavra) {
            int endereco = funcaoHashPorLetraInicial( new PalavraChave(palavra));
        	
            if (endereco != -1) {
                
           
            return    this.vetor[endereco].busca( new PalavraChave(palavra));
             
            } 
            return null;
        }

        static class ListaDuplamenteEncadeada<T> {
            class Nodo {
                public T elemento;
                public Nodo proximo;
                public Nodo anterior;

                public Nodo(T elemento) {
                    this.elemento = elemento;
                    this.proximo = null;
                    this.anterior = null;
                }
            }

            private Nodo inicio;
            private Nodo fim;
            private int nElementos;

            public ListaDuplamenteEncadeada() {
                this.inicio = null;
                this.fim = null;
                this.nElementos = 0;
            }

            public void insereFinal(T elemento) {
                Nodo novo = new Nodo(elemento);
//            	System.out.println(this.fim);
                if (this.inicio == null) {
//                	System.out.println("não tem inicio");
                    this.inicio = novo;
                } else {
                    this.fim.proximo = novo;
                    novo.anterior = this.fim;
                }
                this.fim = novo;
                this.nElementos++;
            }

            public boolean contem(T elemento) {
                Nodo atual = this.inicio;
                while (atual != null) {
                    if (atual.elemento.equals(elemento)) {
                        return true;
                    }
                    atual = atual.proximo;
                }
                return false;
            }

            public void imprime() {
                Nodo atual = this.inicio;
                while (atual != null) {
                    System.out.print(atual.elemento + " ");
                    atual = atual.proximo;
                }
            }
            
            public String toString() {
            	StringBuilder output = new StringBuilder();
                Nodo atual = this.inicio;
                while (atual != null) {
                    output.append(atual.elemento + " ");
                    atual = atual.proximo;
                }
                return output.toString();
            }
        }
    }

    //lista
    static public class Fila<T> {
    	
    	class Nodo {
    		
    		public T elemento;
    		public Nodo proximo;
    		
    		public Nodo(T elemento) {
    			this.elemento = elemento;
    			this.proximo = null;
    		}
    	}

    	private Nodo inicio;
    	private Nodo fim;
    	private int nElementos;

    	public Fila() {
    		this.inicio = null;
    		this.fim = null;
    		this.nElementos = 0;
    	}

    	public int tamanho() {
    		return this.nElementos;
    	}

    	public boolean estaVazia() {
    		return this.nElementos == 0;
    	}

    	public void imprime() {

    		System.out.print("[");
    		Nodo cursor = this.inicio;
    		for (int i = 0; i < this.nElementos; i++) {
    			System.out.print(cursor.elemento + " ");
    			cursor = cursor.proximo;
    		}
    		if(this.nElementos == 0)
    			System.out.println("] Inicio: " + this.inicio + ", Fim: " + this.fim);
    		else
    			System.out.println("] Inicio: " + this.inicio.elemento + ", Fim: " + this.fim.elemento);
    			
    	}

    	public T desenfileira() {

    		if (this.estaVazia()) {
    			System.out.println("Lista vazia. Não é possível remover.");
    			return null;
    		}

    		Nodo nodoRemovido = this.inicio;
    		
    		if(this.nElementos == 1) {
    			this.inicio = null;
    			this.fim = null;
    		} else {
    			this.inicio = nodoRemovido.proximo;

    			nodoRemovido.proximo = null;
    		}

    		this.nElementos--;

    		return nodoRemovido.elemento;

    	}

    	public void enfileira(T elemento) {

    		Nodo novo = new Nodo(elemento);

    		if (this.estaVazia()) {
    			this.inicio = novo;
    		} else {
    			this.fim.proximo = novo;
    		}

    		this.fim = novo;
    		this.nElementos++;
    	}

    	public T espia() {

    		if (this.estaVazia()) {
    			System.out.println("Lista vazia! Não é possível espiar.");
    			return null;
    		}

    		return this.inicio.elemento;
    	}

    	public boolean contem(Integer elemento) {

    		Nodo cursor = this.inicio;
    		for (int i = 0; i < this.nElementos; i++) {
    			if (cursor.elemento.equals(elemento)) {
    				return true;
    			}
    			cursor = cursor.proximo;
    		}

    		return false;
    	}
    }
    
    // pilha
    class Pilha<T> {
    	
    	class Nodo {
    		
    		public T elemento;
    		public Nodo proximo;
    		public Nodo anterior;
    		
    		public Nodo(T elemento) {
    			this.elemento = elemento;
    			this.proximo = null;
    			this.anterior = null;
    		}
    	}
    	
    	private Nodo inicio;
    	private Nodo topo; // fim
    	private int nElementos;
    	
    	public Pilha() {
    		this.inicio = null;
    		this.topo = null;
    		this.nElementos = 0;
    	}
    	
    	public boolean estaVazia() {
    		return this.nElementos == 0;
    	}
    	
    	public int tamanho() {
    		return this.nElementos;
    	}

    	public void imprime() {
    		Nodo cursor = this.topo;
    		for(int i=0;i<this.nElementos;i++) {
    			System.out.println("|\t" + cursor.elemento + "\t|");
    			cursor = cursor.anterior;
    		}
    		System.out.println("----------------------");
    	}
    		
    	public void empilha(T elemento) {
    		
    		Nodo novo = new Nodo(elemento);
    		
    		if(this.estaVazia()) {
    			this.inicio = novo;
    		} else {
    			this.topo.proximo = novo;
    			novo.anterior = this.topo;
    		}
    		
    		this.topo = novo;
    		
    		this.nElementos++;
    	}
    	
    	public T desempilha() {
    		
    		if(this.estaVazia()) {
    			System.out.println("Lista vazia. Não é possível remover.");
    			return null;
    		}
    		
    		Nodo nodoRemovido = this.topo;
    		
    		if(this.nElementos == 1) {
    			
    			this.inicio = null;
    			this.topo = null;
    		} else {
    			
    			this.topo = nodoRemovido.anterior;
    			
    			nodoRemovido.anterior.proximo = null;			
    			nodoRemovido.anterior = null;
    		}
    		
    		this.nElementos--;
    		
    		return nodoRemovido.elemento;
    	}
    	
    	public T espia() {
    		
    		if(this.estaVazia()) {
    			System.out.println("Lista vazia! Não é possível espiar.");
    			return null;
    		}
    		
    		return this.topo.elemento;
    	}
    	
    	public boolean contem(T elemento) {
    		
    		Nodo cursor = this.inicio;
    		for(int i=0;i<this.nElementos;i++) {
    			if(cursor.elemento.equals(elemento)) {
    				return true;
    			}
    			cursor = cursor.proximo;
    		}
    		
    		return false;
    	}
    	
    }

    //árvore
   static class ArvoreBinariaBusca {

    	class Nodo {

    		public PalavraChave elemento;
    		public Nodo esquerdo;
    		public Nodo direito;

    		public Nodo(PalavraChave elemento) {
    			this.elemento = elemento;
    			this.esquerdo = null;
    			this.direito = null;
    		}
    	}

    	public Nodo raiz;
    	public int nElementos;

    	public ArvoreBinariaBusca() {
    		this.raiz = null;
    		this.nElementos = 0;
    	}

    	public int tamanho() {
    		return this.nElementos;
    	}

    	public boolean estaVazia() {
    		return this.raiz == null;
    	}

    	public void imprimeEmLargura() {

    		Fila<Nodo> fila = new Fila<Nodo>();

    		fila.enfileira(this.raiz);
    		while (!fila.estaVazia()) {

    			Nodo cursor = fila.desenfileira();

    			System.out.print(cursor.elemento + " ");

    			if (cursor.esquerdo != null) {
    				fila.enfileira(cursor.esquerdo);
    			}

    			if (cursor.direito != null) {
    				fila.enfileira(cursor.direito);
    			}
    		}

    		System.out.println();

    	}

    	public void imprimePreOrdem() {
    		this.preOrdem(this.raiz);
    		System.out.println();
    	}

    	public void imprimePosOrdem() {
    		this.posOrdem(this.raiz);
    		System.out.println();
    	}

    	public void imprimeEmOrdem() {
    		this.emOrdem(this.raiz);
    		System.out.println();
    	}

    	public void preOrdem(Nodo nodo) {

    		if (nodo == null)
    			return;

    		System.out.print(nodo.elemento + " ");
    		this.preOrdem(nodo.esquerdo);
    		this.preOrdem(nodo.direito);
    	}

    	public void posOrdem(Nodo nodo) {

    		if (nodo == null)
    			return;

    		this.posOrdem(nodo.esquerdo);
    		this.posOrdem(nodo.direito);
    		System.out.print(nodo.elemento + " ");
    	}

    	public void emOrdem(Nodo nodo) {

    		if (nodo == null)
    			return;

    		this.emOrdem(nodo.esquerdo);
    		System.out.print(nodo.elemento + " ");
    		this.emOrdem(nodo.direito);
    	}

    	public void insere(PalavraChave elemento) {
    		this.insere(elemento, this.raiz);
    	}

    	public void insere(PalavraChave elemento, Nodo nodo) {

    		Nodo novo = new Nodo(elemento);

    		if (nodo == null) {
    			this.raiz = novo;
    			this.nElementos++;
    			return;
    		}
    		
    	
    		
    		if (elemento.compareTo(nodo.elemento) < 0) {
    			if (nodo.esquerdo == null) {
    				nodo.esquerdo = novo;
    				this.nElementos++;
    				return;
    			} else {
    				this.insere(elemento, nodo.esquerdo);
    			}
    		}

    		if (elemento.compareTo(nodo.elemento) > 0) {
    			if (nodo.direito == null) {
    				nodo.direito = novo;
    				this.nElementos++;
    				return;
    			} else {
    				this.insere(elemento, nodo.direito);
    			}
    		}
    	}

    	private Nodo maiorElemento(Nodo nodo) {
    		while (nodo.direito != null) {
    			nodo = nodo.direito;
    		}
    		return nodo;
    	}

    	private Nodo menorElemento(Nodo nodo) {
    		while (nodo.esquerdo != null) {
    			nodo = nodo.esquerdo;
    		}
    		return nodo;
    	}

    	public boolean remove(PalavraChave elemento) {
    		return this.remove(elemento, this.raiz) != null;
    	}

    	private Nodo remove(PalavraChave elemento, Nodo nodo) {

    		if (nodo == null) {
    			System.out.println("Valor não encontrado");
    			return null;
    		}

    		if (elemento.compareTo(elemento) < 0) {
    			nodo.esquerdo = this.remove(elemento, nodo.esquerdo);
    		} else if (elemento.compareTo(elemento) > 0) {
    			nodo.direito = this.remove(elemento, nodo.direito);
    		} else {

//    	    	if(nodo.esquerdo == null && nodo.direito == null) {
//    	    		return null;
//    	    	}

    			if (nodo.esquerdo == null) {
    				this.nElementos--;
    				return nodo.direito;
    			} else if (nodo.direito == null) {
    				this.nElementos--;
    				return nodo.esquerdo;
    			} else {
    				Nodo substituto = this.menorElemento(nodo.direito);
    				nodo.elemento = substituto.elemento;
    				this.remove(substituto.elemento, nodo.direito);
    			}
    		}

    		return nodo;
    	}

    	public PalavraChave busca(PalavraChave elemento) {
    		return this.busca(elemento, this.raiz);

    	}

    	public PalavraChave busca(PalavraChave elemento, Nodo nodo) {

    		if (nodo == null) {
    			return null;
    		}

    		if (elemento.compareTo(nodo.elemento) < 0) {
    			return this.busca(elemento, nodo.esquerdo);
    		} else if (elemento.compareTo(nodo.elemento) > 0) {
    			return this.busca(elemento, nodo.direito);
    		} else {
    			return elemento;
    		}
    	}

    	private int altura(Nodo nodo) {

    		if (nodo == null) {
    			return -1;
    		}

    		int alturaEsquerda = this.altura(nodo.esquerdo) + 1;
    		int alturaDireita = this.altura(nodo.direito) + 1;

    		int altura = alturaEsquerda > alturaDireita ? alturaEsquerda : alturaDireita;

    		return altura;

    	}

    	public int altura() {
    		return this.altura(this.raiz);
    	}
    }
    
    static class PalavraChave implements Comparable<PalavraChave>{
        private String palavra;
        private Av3.Main.HashColisaoExterior.ListaDuplamenteEncadeada <Integer> posicoes;

        public PalavraChave(String palavra) {
            this.palavra = palavra.toLowerCase(); // Convertendo para minúscula
            this.posicoes = new Av3.Main.HashColisaoExterior.ListaDuplamenteEncadeada <Integer>(); 
        }

		@Override
        public String toString() {
            return palavra + " " + posicoes ;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PalavraChave other = (PalavraChave) obj;
            return this.palavra.equalsIgnoreCase(other.palavra); // Comparação case-insensitive
        }

		@Override
		public int compareTo(PalavraChave o) {
			return this.palavra.compareTo(o.palavra);
		}
    }

    static class LeitorArquivo {
        private HashColisaoExterior<PalavraChave> tabelaHash;
    

        public LeitorArquivo(int capacidade) {
            this.tabelaHash = new HashColisaoExterior<>(capacidade);
        
       
        }

        public void carregarPalavrasChave(String caminhoPalavrasChave) {
            File file = new File(caminhoPalavrasChave);
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] palavras = scanner.nextLine().trim().toLowerCase().split("\\s+"); // Convertendo para minúscula
                    
                    for(String palavra: palavras) {
                    	 // System.out.println("Carregando palavra-chave: " + palavra);
                          tabelaHash.insere(new PalavraChave(palavra));
                    }
                  
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo de palavras-chave: " + e.getMessage());
            }
        }

        public void lerArquivoTexto(String caminhoArquivo) {
            File file = new File(caminhoArquivo);
            try (Scanner scanner = new Scanner(file)) {
                int numeroLinha = 1;

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String[] palavras = linha.split("\\s+");

                    for (String palavra : palavras) {
                    	
                    	if(palavra.isEmpty()) {
                    		continue;
                    	}
                    	
                        palavra = palavra.toLowerCase();  //normalização
                      //  System.out.println(palavra + "PALAVRA");
                        PalavraChave palavraAtual = tabelaHash.busca(palavra);
                     //   System.out.println(palavraAtual + "BUSCA");
                       if (palavraAtual != null) {
                           palavraAtual.posicoes.insereFinal(numeroLinha);
                           System.out.println(palavraAtual);
                        }
                    }
                    numeroLinha++;
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
          //  imprimirOcorrencias();
        }
        
        private void imprimirOcorrencias() {
            for (int i = 0; i < tabelaHash.vetor.length; i++) {
                // Percorre cada árvore binária de cada bucket
                ArvoreBinariaBusca arvore = tabelaHash.vetor[i];
                
                // Imprime as palavras e suas posições
                arvore.imprimeEmOrdem();
                
                // Isso já vai imprimir as palavras e suas posições
            }
        }

        public void imprimeIndiceRemissivo() {
            tabelaHash.imprimeTabela();
        }
    }

    public static void main(String[] args) {
        LeitorArquivo leitor = new LeitorArquivo(26);
        HashColisaoExterior hash = new HashColisaoExterior(26);
        leitor.carregarPalavrasChave("C:\\Users\\alunok11\\Downloads\\Índice Remissivo\\chaves.txt");
        leitor.lerArquivoTexto("C:\\Users\\alunok11\\Downloads\\Índice Remissivo\\texto.txt");
        leitor.imprimeIndiceRemissivo();
       
        
    }
}