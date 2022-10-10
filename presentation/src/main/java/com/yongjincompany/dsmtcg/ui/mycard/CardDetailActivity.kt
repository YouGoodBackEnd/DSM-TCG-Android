package com.yongjincompany.dsmtcg.ui.mycard

import android.os.Bundle
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityCardDetailBinding
import com.yongjincompany.dsmtcg.extensions.loadFromUrl

class CardDetailActivity : BaseActivity<ActivityCardDetailBinding>(
    R.layout.activity_card_detail
) {
    private var description: String = ""
    private var name: String = ""
    private var grade: String = ""
    private var cardImage: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        description = intent.getStringExtra("DESCRIPTION").toString()
        name = intent.getStringExtra("NAME").toString()
        grade = intent.getStringExtra("GRADE").toString()
        cardImage = intent.getStringExtra("CARD_IMAGE").toString()
    }

    override fun initView() {
        binding.ivCard.loadFromUrl(cardImage)
        binding.cardExplain.text = description
        binding.cardName2.text = name
        binding.cardGrade2.text = grade

        binding.ivExit.setOnClickListener {
            finish()
        }
    }

}