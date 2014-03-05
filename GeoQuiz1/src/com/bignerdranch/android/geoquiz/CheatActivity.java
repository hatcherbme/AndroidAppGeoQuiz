package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends Activity {
	private static final String TAG = "CheatActivity";
	public static final String EXTRA_ANSWER_IS_TRUE =
			"com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN =
			"com.bignerdranch.android.geoquiz.answer_shown";
	private static final String CHEAT_TEXT = "cheat_text";
	private boolean mAnswerIsTrue;
	
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	private boolean savedanswer;
	private int APIVAR;
	private String APIVAR2;
	private TextView APIleveltextview;
	
	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		APIleveltextview = (TextView)findViewById(R.id.sdk_view);
		APIVAR=Build.VERSION.SDK_INT;
		APIVAR2=Integer.toString(APIVAR);
		APIleveltextview.setText("API level " + APIVAR2);
		
		// Answer will not be shown until the user
		// presses the button
		setAnswerShownResult(false);
		
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mAnswerIsTrue) {
					savedanswer=true;
					mAnswerTextView.setText(R.string.true_button);
				} else {
					savedanswer=true;
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}
		});
		if (savedInstanceState != null) {
			savedanswer = savedInstanceState.getBoolean(CHEAT_TEXT);
		}
		if  (savedanswer==true)
		{
			if (mAnswerIsTrue) {
				savedanswer=true;
				setAnswerShownResult(true);
				mAnswerTextView.setText(R.string.true_button);
			} else {
				savedanswer=true;
				mAnswerTextView.setText(R.string.false_button);
				setAnswerShownResult(true);
			}
		}
	}
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		savedInstanceState.putBoolean(CHEAT_TEXT, savedanswer);
	}
}
