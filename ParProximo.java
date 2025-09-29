public class ParProximo {

    public static double menorDistanciaRef = Double.MAX_VALUE;
    public static Ponto[] parMenorDistancia = new Ponto[2];

    //Distancia entre dois pontos
    public static double calcularDistancia(Ponto ponto1,Ponto ponto2){
        double calculox = (double) (ponto2.x - ponto1.x)*(ponto2.x - ponto1.x);
        double calculoy = (double) (ponto2.y - ponto1.y)*(ponto2.y - ponto1.y);

        return Math.sqrt(calculox+calculoy);
    }

    //Distancia de um ponto para origem (0,0)
    public static Ponto distanciaOrigem(Ponto p1,Ponto p2){
        double distancia1 = (p1.x*p1.x) + (p1.y*p1.y);
        double distancia2 = (p2.x*p2.x) + (p2.y*p2.y);
        if(distancia1 <= distancia2){
            return p1;
        }else{
            return p2;
        }
    }

    public static void parComMenorDistancia(Ponto[] pontos){
        menorDistancia(pontos,0,pontos.length-1);
        for(Ponto p:parMenorDistancia){
            System.out.println(p.toString());
        }
        System.out.println("Menor distancia encontrada: "+menorDistanciaRef);
    }

    public static void menorDistancia(Ponto[] pontos,int inicio,int fim){
        if(inicio < fim){
            int meio = (fim+inicio)/2;
            menorDistancia(pontos,inicio,meio);
            menorDistancia(pontos,meio+1,fim);
            merge(pontos,inicio,meio,fim);
        }
    }

    public static void merge(Ponto[] pontos,int inicio,int meio,int fim){
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;

        Ponto[] esq = new Ponto[n1];
        Ponto[] dir = new Ponto[n2];

        for(int i = 0 ; i < n1 ; i++){
            esq[i] = pontos[inicio + i];
        }

        for(int j = 0 ; j < n2 ; j++){
            dir[j] = pontos[meio + 1 + j];
        }

        int i = 0 , j = 0 , k = inicio;

        while(i < n1 && j < n2){
            double distancia = calcularDistancia(esq[i],dir[j]);
            if(distancia < menorDistanciaRef){
                menorDistanciaRef = distancia;
                parMenorDistancia[0]=esq[i];
                parMenorDistancia[1]=dir[j];
            }
            if(distanciaOrigem(esq[i],dir[j]) == esq[i]){
               pontos[k] = esq[i];
               i++;
            }else{
                pontos[k] = dir[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            pontos[k] = esq[i];
            i++;
            k++;
        }

        while(j < n2){
            pontos[k] = dir[j];
            j++;
            k++;
        }

    }

    public static void main(String args[]){
        Ponto[] pontos1 = {
                new Ponto(2,4),
                new Ponto(7,-1),
                new Ponto(-3,6),
                new Ponto(1,0),
                new Ponto(5,2)
        };
        Ponto[] pontos2 = {
                new Ponto(6,-1),
                new Ponto(-2,3),
                new Ponto(3,2),
                new Ponto(1,0),
                new Ponto(5,7),
                new Ponto(10,0),
                new Ponto(4,1)
        };
        Ponto[] pontos3 = {
                new Ponto(2,7),
                new Ponto(6,4),
                new Ponto(1,8),
                new Ponto(5,3)
        };
        Ponto[] pontos4 = {
                new Ponto(-3,5),
                new Ponto(1,2),
                new Ponto(-1,4),
        };
        Ponto[] pontos5 = {
                new Ponto(1, 2),
                new Ponto(4, 6),
                //new Ponto(2, 3)
        };
        Ponto[] pontos6 = {
                new Ponto(1, 1),
                new Ponto(2, 2),
                new Ponto(1, 2),
                new Ponto(0, 0),
                new Ponto(3, 4),
                new Ponto(2, 5),
                new Ponto(5, 1),
                new Ponto(4, 4),
                new Ponto(7, 7),
                new Ponto(8, 8),
                new Ponto(10, 10),
                new Ponto(-2, -3),
                new Ponto(-4, -5),
                new Ponto(5, 2)
        };



        long tempoInicial = System.nanoTime();

        parComMenorDistancia(pontos5);
        //Ponto[] menorDistancia2 = parComMenorDistancia(pontos2);

        long tempoFinal = System.nanoTime();

        double tempoTotal = (tempoFinal-tempoInicial)/1_000_000.0;

        System.out.println(tempoTotal);

    }
}
