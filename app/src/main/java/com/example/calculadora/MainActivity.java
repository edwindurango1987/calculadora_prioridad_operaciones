package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Button vCero, vUno, vDos, vTres, vCuatro, vCinco, vSeis, vSiete, vOcho, vNueve,
            vPunto, vClear, vIgual, vMas, vMenos, vPor, vDividido,vPorcentaje,vMasEntreMenos;

    TextView vResultado;
    Double  vNumero1 = Double.NaN;
    Double vNumero2 =Double.NaN;
    Character vOperacion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vCero = findViewById(R.id.button_0);
        vUno = findViewById(R.id.button_1);
        vDos = findViewById(R.id.button_2);
        vTres = findViewById(R.id.button_3);
        vCuatro = findViewById(R.id.button_4);
        vCinco = findViewById(R.id.button_5);
        vSeis = findViewById(R.id.button_6);
        vSiete = findViewById(R.id.button_7);
        vOcho = findViewById(R.id.button_8);
        vNueve = findViewById(R.id.button_9);
        vMas = findViewById(R.id.button_suma);
        vMenos = findViewById(R.id.button_resta);
        vPor = findViewById(R.id.button_multiplica);
        vDividido = findViewById(R.id.button_divide);
        vPunto = findViewById(R.id.button_decimal);
        vIgual = findViewById(R.id.button_igual);
        vClear = findViewById(R.id.button_ac);
        vPorcentaje=findViewById(R.id.button_porcentaje);
        vResultado = findViewById(R.id.result);
        vMasEntreMenos=findViewById(R.id.button_mas_entre_menos);

        vCero.setOnClickListener(this);
        vUno.setOnClickListener(this);
        vDos.setOnClickListener(this);
        vTres.setOnClickListener(this);
        vCuatro.setOnClickListener(this);
        vCinco.setOnClickListener(this);
        vSeis.setOnClickListener(this);
        vSiete.setOnClickListener(this);
        vOcho.setOnClickListener(this);
        vNueve.setOnClickListener(this);
        vMas.setOnClickListener(this);
        vMenos.setOnClickListener(this);
        vPor.setOnClickListener(this);
        vDividido.setOnClickListener(this);
        vPunto.setOnClickListener(this);
        vIgual.setOnClickListener(this);
        vClear.setOnClickListener(this);
        vPorcentaje.setOnClickListener(this);
        vMasEntreMenos.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                agregarDigito('0');
                break;

            case R.id.button_1:
                agregarDigito('1');
                break;

            case R.id.button_2:
                agregarDigito('2');
                break;

            case R.id.button_3:
                agregarDigito('3');

                break;

            case R.id.button_4:
                agregarDigito('4');
                break;

            case R.id.button_5:
                agregarDigito('5');
                break;

            case R.id.button_6:
                agregarDigito('6');
                break;

            case R.id.button_7:
                agregarDigito('7');
                break;

            case R.id.button_8:
                agregarDigito('8');
                break;

            case R.id.button_9:
                agregarDigito('9');
                break;

            case R.id.button_decimal:
                agregarDigito(',');
                break;

            case R.id.button_ac:
                 borrarResultado();
                break;

            case R.id.button_suma:
                agregarDigito('+');
                break;

            case R.id.button_resta:
                agregarDigito('-');
                break;

            case R.id.button_multiplica:
                agregarDigito('*');
                break;

            case R.id.button_divide:
                agregarDigito('/');
                break;

            case R.id.button_igual:
                calcularResultado();
                break;

            case R.id.button_porcentaje:
                obtenerPorcentaje();
                break;

            case R.id.button_mas_entre_menos:
                cambiarSigno();
                calcularResultado();
                break;

        }
    }

    private void agregarDigito(char digit) {
        if (vResultado.getText().toString().equals("0")) {
            vResultado.setText(String.valueOf(digit));
        } else {
            vResultado.append(String.valueOf(digit));
        }
    }


    private void borrarResultado() {
        vNumero1 = Double.NaN;
        vNumero2 = Double.NaN;
        vOperacion = ' ';
        vResultado.setText("0");
    }


    public Double obtenerPorcentaje(){
       Double total=calcularResultado()/100.0;
        vResultado.setText(String.format("%.1f", total));
        return total;
    }

    public Double cambiarSigno(){
        vNumero1 = (Double.parseDouble(vResultado.getText().toString()));
        //Double resultado;
        vNumero1 = 0-vNumero1;
        vResultado.setText(String.format("%.1f", vNumero1));
        return vNumero1;

    }



    private Double calcularResultado() {
        String expression = vResultado.getText().toString();
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        // Separar números y operadores
        String[] parts = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
        for (String part : parts) {
            if (part.matches("\\d+(\\.\\d+)?")) {
                numbers.add(Double.parseDouble(part));
            } else {
                operators.add(part.charAt(0));
            }
        }


        // Resolver las operaciones de multiplicación y división
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '*' || operators.get(i) == '/') {
                double result;
                if (operators.get(i) == '*') {
                    result = numbers.get(i) * numbers.get(i + 1);
                } else {
                    result = numbers.get(i) / numbers.get(i + 1);
                }
                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }

        // Resolver las operaciones de suma y resta
        double result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '+') {
                result += numbers.get(i + 1);
            } else  {
                result -= numbers.get(i + 1);
            }

        }

        vResultado.setText(String.format("%.1f", result));
        return result;
    }

    }


