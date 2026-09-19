package cn.edu.sicnu.cs.lyx.First

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import cn.edu.sicnu.cs.lyx.First.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            binding.tvMsg.text = getString(R.string.click_msg)
        }
    }
}
