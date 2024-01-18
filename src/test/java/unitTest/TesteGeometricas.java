package unitTest;// Bibliotecas

import br.com.iterasys.Geometricas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

// Classe
public class TesteGeometricas {

    // Atributos

    // Funcões e Métodos

    @Test
    public void testeAreaDoCuboPositivo(){
        // Configura
        // Valores de entrada
        double area = 3;
        // Valores de saída
        double resultadoEsperado = 54;

        // Executa
        double resultadoAtual = Geometricas.areaDoCubo(area);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testeAreaDoCuboNegativo() {
        // Configura
        // Valores de entrada
        double area = 2;
        // Valores de saída
        double resultadoEsperado = 54;

        // Executa
        double resultadoAtual = Geometricas.areaDoCubo(area);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }

    @Test
    public void testeAreaDoParalelogramo(){
        // Configura
        // Valores de entrada
        double area = 13;
        double altura = 22;
        // Valores de saída
        double resultadoEsperado = 286;

        // Executa
        double resultadoAtual = Geometricas.areaDoPararelogramo(area, altura);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @Test
    public void testeAreaPiramide(){
        // Configura
        // Valores de entrada
        double ladoBase = 15;
        double alturaPiramide = 12;
        // Valores de saída
        double resultadoEsperado = 585;

        // Executa
        double resultadoAtual = Geometricas.areaPiramide(ladoBase, alturaPiramide);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual, 0.01);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/csv/massaTestePiramide.csv")
    public void testeAreaPiramideLendoArquivo(double ladoBase, double alturaPiramide,double resultadoEsperado ){
        // Configura
        // Os dados de entrada e o resultado vem da lista

        // Valores de saída

        // Executa
        double resultadoAtual = Geometricas.areaPiramide(ladoBase, alturaPiramide);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual, 0.01);
    }
}
