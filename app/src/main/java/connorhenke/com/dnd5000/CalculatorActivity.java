package connorhenke.com.dnd5000;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import connorhenke.com.dnd5000.databinding.ActivityCalculatorBinding;

public class CalculatorActivity extends AppCompatActivity {

    private ActivityCalculatorBinding binding;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator);
        setupClickListeners();
        calculator = new Calculator();
    }

    private void clearTop() {
        binding.calculatorTextView.setText("");
    }

    private void clearAll() {
        clearTop();
        calculator = new Calculator();
        binding.setCalculator(calculator);
    }

    private int evaluateCalculator() {
        if (binding.calculatorTextView.getText().length() > 0) {
            try {
                int value = (int) Calculator.eval(binding.calculatorTextView.getText().toString());
                binding.calculatorTextView.setText("");
                return value;
            } catch (UnsupportedOperationException e) {
                Toast.makeText(this, "Invalid syntax", Toast.LENGTH_SHORT).show();
            }
        }
        return 0;
    }

    private void setupClickListeners() {
        binding.calculator0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("0");
            }
        });
        binding.calculator1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("1");
            }
        });
        binding.calculator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("2");
            }
        });
        binding.calculator3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("3");
            }
        });
        binding.calculator4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("4");
            }
        });
        binding.calculator5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("5");
            }
        });
        binding.calculator6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("6");
            }
        });
        binding.calculator7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("7");
            }
        });
        binding.calculator8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("8");
            }
        });
        binding.calculator9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("9");
            }
        });
        binding.calculatorAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("+");
            }
        });
        binding.calculatorSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("-");
            }
        });
        binding.calculatorDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("/");
            }
        });
        binding.calculatorMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append("*");
            }
        });
        binding.calculatorDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.calculatorTextView.append(".");
            }
        });
        binding.calculatorClearEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearTop();
            }
        });
        binding.calculatorClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
            }
        });
        binding.calculatorBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = binding.calculatorTextView.getText().length();
                if (length > 0) {
                    binding.calculatorTextView.setText(binding.calculatorTextView.getText().subSequence(0, length - 1));
                }
            }
        });
        binding.calculatorCp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = evaluateCalculator();
                calculator.addCp(value);
                binding.setCalculator(calculator);
            }
        });
        binding.calculatorSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = evaluateCalculator();
                calculator.addSp(value);
                binding.setCalculator(calculator);
            }
        });
        binding.calculatorEp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = evaluateCalculator();
                calculator.addEp(value);
                binding.setCalculator(calculator);
            }
        });
        binding.calculatorGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = evaluateCalculator();
                calculator.addGp(value);
                binding.setCalculator(calculator);
            }
        });
        binding.calculatorPp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = evaluateCalculator();
                calculator.addPp(value);
                binding.setCalculator(calculator);
            }
        });
        binding.calculatorSplitUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer splitNumber = Integer.parseInt(binding.calculatorSplitNumber.getText().toString());
                binding.calculatorSplitNumber.setText("" + (splitNumber + 1));
            }
        });
        binding.calculatorSplitDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer splitNumber = Integer.parseInt(binding.calculatorSplitNumber.getText().toString());
                if (splitNumber > 1) {
                    binding.calculatorSplitNumber.setText("" + (splitNumber - 1));
                }
            }
        });
        binding.calculatorSplitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer splitNumber = Integer.parseInt(binding.calculatorSplitNumber.getText().toString());
                calculator.split(splitNumber);
                binding.setCalculator(calculator);
            }
        });
    }
}
