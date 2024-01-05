// 1 - Pacote
package br.com.iterasys;
// 2 - Bibliotecas

// 3 - Classe
public class Geometricas {
    // 3.1 Atributos

    // 3.2 Funções e Métodos
    public static double areaDoCubo(double area){
        return 6 * Math.pow(area, 2);

    }

    public static double areaDoPararelogramo(double base, double altura){
        return base * altura;

    }

    public static double areaPiramide(double ladoBase, double alturaPiramide ){

        double areaBase = ladoBase * ladoBase;
        double perimetroBase = 4 * ladoBase;
        double areaLateral = (perimetroBase * alturaPiramide) / 2;
        return areaBase + areaLateral;
    }

}
