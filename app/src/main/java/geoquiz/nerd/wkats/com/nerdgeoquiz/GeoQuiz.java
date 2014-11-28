package geoquiz.nerd.wkats.com.nerdgeoquiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GeoQuiz extends ActionBarActivity {

    private Button mBtnFalse, mBtnTrue; //Instancia de los botones de Cierto y Falso
    private Button mBtnNext; //Instancia del botón Siguiente
    private Button mBtnPrev; //Instancia del botón Anterior
    private TextView mQuestionTextView; //Text View donde se visualiza la pregunta actual
    private TrueFalse[] mQuestionBank = new TrueFalse[] {   //Banco de preguntas TrueFalse
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };
    private int mCurrentIndex;  //Índice de la pregunta actual
    private static String TAG = "GeoQuiz";  //Tag para identificar la aplicación
    private static final String KEY_INDEX = "index";    //Llave de asociación en el bundle del valor para mCurrentIndex

    private void updateQuestion(){  //Actualizar la pregunta
        mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;   //Se aumenta el índice procupando que no pase del valor total
        int question = mQuestionBank[mCurrentIndex].getQuestion();  //Se obtiene el ID de la pregunta correspondiente
        mQuestionTextView.setText(question);    //Se actualiza el texto de mQuestionTextView
    }
    private void updateQuestionBack() { //Actualiza la pregunta en orden descendente
        mCurrentIndex-=1;       //Se retrocede en 1 el valor del índice
        if(mCurrentIndex<0)     //Si el resultado es negativo...
            mCurrentIndex=mQuestionBank.length-1;   //se manda a la última pregunta de nuestro banco
        int question = mQuestionBank[mCurrentIndex].getQuestion();  //Se obtiene el id de la pregunta actual
        mQuestionTextView.setText(question);    //Se actualiza el texto del mQuestionTextView
    }
    private void checkAnswer(boolean userPressedTrue) {     //Checador de respuestas
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();   //Se obtiene el valor booleano de la pregunta actual
        int messageResId = 0;           //El ID del mensaje que mostrará el toast
        if (userPressedTrue == answerIsTrue) {  //Si la pregunta es correcta, se obtiene el id de el mensaje correcto
            messageResId = R.string.correct_toast;
        } else {        //Si no es correcta, se obtiene el mensaje para el resultado incorrecto
            messageResId = R.string.incorrect_toast;
        }
        /*Se muestra el mensaje correspondiente*/
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {   //Si se ha guardado algo...
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0); //Se actualiza el valor del índice con el índice guardaado
        }
        Log.d(TAG, "onCreate(Bundle) called");  //Un Aviso al Log de que se ha llegado aquí
        setContentView(R.layout.activity_geo_quiz);
        /*Asociación de los objetos gráficos*/
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mBtnFalse = (Button)findViewById(R.id.false_button);
        mBtnTrue = (Button)findViewById(R.id.true_button);
        mBtnNext = (Button)findViewById(R.id.next_button);
        mBtnPrev = (Button)findViewById(R.id.previous_button);
        /*Creación de las subrutinas de atención a un evento de click*/
        mBtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mBtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        mBtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                updateQuestionBack();
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                updateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                updateQuestion();
            }
        });
    updateQuestion(); //Se llama al método de actualización de pregunta en el pantalla

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_geo_quiz, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex-1);
    }

}