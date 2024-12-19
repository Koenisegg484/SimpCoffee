package com.practiceprojects.simpcoffee.Activity

import android.content.Intent
import android.os.Bundle
import com.practiceprojects.simpcoffee.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {

    lateinit var binding: ActivityIntroBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }

    }
}