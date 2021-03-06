package com.chinalwb.are.styles;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.AREditor;
import com.chinalwb.are.Constants;
import com.chinalwb.are.spans.AreImageSpan;

public class ARE_Image implements IARE_Style {
  
	private ImageView mInsertImageView;

	private EditText mEditText;
	
	private Context mContext;
	
	/**
	 * 
	 * @param emojiImageView
	 */
	public ARE_Image(ImageView emojiImageView, EditText editText) {
		this.mInsertImageView = emojiImageView;
		this.mEditText = editText;
		this.mContext = editText.getContext();
		setListenerForImageView(this.mInsertImageView);
	}

	@Override
	public void setListenerForImageView(ImageView imageView) {
		imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			  openImageChooser();
			}
		});
	} // #End of setListenerForImageView(..)
	
	/**
	 * Open system image chooser page.
	 */
	private void openImageChooser() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    ((Activity) this.mContext).startActivityForResult(intent, AREditor.REQ_IMAGE);
  }

	
	/**
	 * 
	 * @param uri
	 */
	public void insertImage(Uri uri) {
//      AreImageSpan imageSpan = new AreImageSpan(mContext, uri);
//	  
//	  int start = this.mEditText.getSelectionStart();
//	  int end = this.mEditText.getSelectionEnd();
//	  
//	  Editable editable = this.mEditText.getEditableText();
//	  
//	  SpannableStringBuilder ssb = new SpannableStringBuilder();
//	  ssb.append(Constants.ZERO_WIDTH_SPACE_STR);
//	  ssb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//	  
//	  editable.replace(start, end, ssb);
	  int start = this.mEditText.getSelectionStart();
	  
	  LinearLayout rootContainerLayout = (LinearLayout) this.mEditText.getParent();
	  LinearLayout imageContainerLayout = new LinearLayout(mContext);
	  LinearLayout.LayoutParams imageContainerLayoutParams = new LinearLayout.LayoutParams(
			  LinearLayout.LayoutParams.MATCH_PARENT, // Width
			  LinearLayout.LayoutParams.WRAP_CONTENT  // Height
	  );
	  imageContainerLayoutParams.gravity = Gravity.CENTER_HORIZONTAL; // @Todo
	  imageContainerLayout.setOrientation(LinearLayout.HORIZONTAL);
	  imageContainerLayout.setLayoutParams(imageContainerLayoutParams);
	  
	  //
	  // 1. Left
	  AREditText leftEditText = new AREditText(mContext);
	  LinearLayout.LayoutParams leftEditTextLayoutParams = new LinearLayout.LayoutParams(
			  LinearLayout.LayoutParams.WRAP_CONTENT, // Width
			  LinearLayout.LayoutParams.MATCH_PARENT  // Height
	  );
	  leftEditText.setLayoutParams(leftEditTextLayoutParams);
	  imageContainerLayout.addView(leftEditText);
	  
	  //
	  // 2. Image
	  ImageView imageView = new ImageView(mContext);
	  LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(
			  LinearLayout.LayoutParams.WRAP_CONTENT, // Width
			  LinearLayout.LayoutParams.WRAP_CONTENT  // Height
	  );
	  imageView.setImageURI(uri);
	  imageView.setLayoutParams(imageViewLayoutParams);
	  imageContainerLayout.addView(imageView);
	  
	  //
	  // 3. Right
	  AREditText rightEditText = new AREditText(mContext);
	  LinearLayout.LayoutParams rightEditTextLayoutParams = new LinearLayout.LayoutParams(
			  LinearLayout.LayoutParams.WRAP_CONTENT, // Width
			  LinearLayout.LayoutParams.MATCH_PARENT  // Height
	  );
	  rightEditText.setLayoutParams(rightEditTextLayoutParams);
	  imageContainerLayout.addView(rightEditText);
	  
	  rootContainerLayout.addView(imageContainerLayout);
	}
	
	@Override
	public void applyStyle(Editable editable, int start, int end) {
		// Do nothing
	}

	@Override
	public ImageView getImageView() {
		return this.mInsertImageView;
	}

	@Override
	public void setChecked(boolean isChecked) {
		// Do nothing
	}
}
