package com.youngman.mop.view.boardcreate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.youngman.mop.R;
import com.youngman.mop.data.BoardCreateRequest;
import com.youngman.mop.data.BoardType;
import com.youngman.mop.util.PrefUtils;
import com.youngman.mop.util.ToastUtils;
import com.youngman.mop.view.boardcreate.presenter.BoardCreateContract;
import com.youngman.mop.view.boardcreate.presenter.BoardCreatePresenter;

public class BoardCreateActivity extends Activity implements BoardCreateContract.View {

    private Context context;
    private EditText etName;
    private EditText etContent;
    private RadioGroup rgBoardType;
    private RadioButton btnPost;
    private RadioButton btnNotice;
    private Button btnCreateBoard;
    private BoardCreateContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_board_create);
        init();
    }

    private void init() {
        context = getApplicationContext();
        etName = (EditText) findViewById(R.id.et_name);
        etContent = (EditText) findViewById(R.id.et_content);
        rgBoardType = (RadioGroup) findViewById(R.id.rg_board_type);
        btnPost = (RadioButton) findViewById(R.id.btn_post);
        btnNotice = (RadioButton) findViewById(R.id.btn_notice);
        btnCreateBoard = (Button) findViewById(R.id.btn_create_board);
        presenter = new BoardCreatePresenter(this);

        Long clubId = getIntent().getLongExtra("EXTRA_CLUB_ID", 0);

        btnCreateBoard.setOnClickListener(v -> presenter.callCreateBoard(BoardCreateRequest.builder()
                .title(etName.getText().toString())
                .content(etContent.getText().toString())
                .writer(PrefUtils.readMemberNameFrom(context))
                .clubId(clubId)
                .boardType(btnPost.isChecked() ? BoardType.POST : BoardType.NOTICE)
                .build()
        ));
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startBoardFragment() {
        ToastUtils.showToast(context, "게시글 저장 성공");
        Intent intent = new Intent();
        intent.putExtra("result", "2222");
        setResult(2222, intent);
        finish();
    }


}
