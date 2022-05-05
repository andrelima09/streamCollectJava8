package treinamento;

import java.util.*;
import java.util.stream.Collectors;

public class StreamCollectJava8 {

    public static void main(String[] arg){

        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        //fornecedor, acumulação, combinação
        List<Integer> collect = list.stream()
                .collect(
                        () -> new ArrayList<>(), //fornecedor -> Objeto que eu instacio para receber os dados do steam, nesse caso, um ArrayList
                        (l, e) -> l.add(e), //acumulador -> funcao que eu adiciono os dados no objeto instanciado
                        (l1, l2) -> l1.addAll(l2) //combinação -> onde eu adiciono em uma única lista o que poderia estar sendo feito em uma outra thread.
                );

        System.out.println(collect); //[1, 2, 3, 4, 5, 6]


        //toList
        List<Integer> collect2 = list.stream()
                .collect(Collectors.toList());

        System.out.println(collect2); //[1, 2, 3, 4, 5, 6]


        //join
        String str = list.stream()
                .map(n -> n.toString())
                .collect(Collectors.joining());//concatena em uma string os dados de todoas as posições da lista. O retorno tem que ser uma STRING.
                //tbm pode ser usado joiining(";") -> passando um caracter como parâmetro, e a concatenação será feira separando pelo caracter definido.

        System.out.println(str); //123456


        //averaging -> Média
        Double media = list.stream()
                .collect(Collectors.averagingInt(n -> n.intValue())); // recebe a média de todos os valores inteiros da lista.

        System.out.println(media); //3.5

        //summing - somar
        Integer soma = list.stream()
                .collect(Collectors.summingInt(n -> n.intValue())); //Faz a soma de todos os valores inteiros da lista.

        System.out.println(soma); //21

        //summarizing -> Resumo
        IntSummaryStatistics statisticscs = list.stream()
                .collect(Collectors.summarizingInt(n -> n.intValue()));//Retorna um objeto que já contem vários cálculos como

        System.out.println(statisticscs.getMax());
        System.out.println(statisticscs.getAverage());
        System.out.println(statisticscs.getCount());
        System.out.println(statisticscs.getMin());
        System.out.println(statisticscs.getSum());

        //count

        Long count = list.stream()
                .collect(Collectors.counting()); //imprime o numero de elementos na lista

        System.out.println(count);

        //groupingBy -> É agrupado por uma ou mais chaves de acordo com a regra
        Map<Integer, List<Integer>> groupingBy = list.stream()
                .collect(Collectors.groupingBy((n) -> n % 3)); // Nesse caso será agrupado onde a chave é o resto da divisão.

        System.out.println(groupingBy); //{0=[3, 6], 1=[1, 4], 2=[2, 5]}

        //partitionBy -> agrupo um mapa que sempre terá a chave como Boolean.
        Map<Boolean, List<Integer>> partitionBy = list.stream()
                .collect(Collectors.partitioningBy((n) -> n % 2 == 0)); //Nesse caso é agrupado por números que a divisão por 2 é igual a 0.

        System.out.println(partitionBy); //{false=[1, 3, 5], true=[2, 4, 6]}

        //toMap -> É um modo mais customizado de vc criar um mapa.
        Map<Integer, Integer> toMap = list.stream()
                        .collect(Collectors.toMap(n -> n, n -> n * 2)); //Aqui 'n -> n' estamos dizendo que a chave é o próprio numero
                                                                        //E 'n -> n * 2' estamos dizendo que o valor é o número multiplicado por 2

        System.out.println(toMap); //{1=2, 2=4, 3=6, 4=8, 5=10, 6=12}

    }

}
