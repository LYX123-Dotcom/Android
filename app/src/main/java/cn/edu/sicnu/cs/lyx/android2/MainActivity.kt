package cn.edu.sicnu.cs.lyx.android2

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.edu.sicnu.cs.lyx.android2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adviser: ProgramAdviser
    private var textViewCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adviser = ProgramAdviser()

        binding.btnAddTextview.setOnClickListener {
            textViewCount++
            val tv = TextView(this)
            tv.text = getString(R.string.new_textview_fmt, textViewCount)
            tv.textSize = 16f
            tv.setTextColor(getColor(R.color.black))
            binding.containerTextviews.addView(tv)
            binding.scrollRegion1.post {
                binding.scrollRegion1.fullScroll(View.FOCUS_DOWN)
            }
        }

        binding.btnQuery.setOnClickListener {
            val query = binding.editQuery.text.toString()
            val resultResId = adviser.getAdviceRes(query)
            binding.textResult.text = getString(resultResId)
        }
    }
}
