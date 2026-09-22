package cn.edu.sicnu.cs.lyx.First

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet


class MainActivity : AppCompatActivity() {


    private lateinit var tvMsg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ---------- 1. 根布局：代码 new 一个 ConstraintLayout ----------
        val root = ConstraintLayout(this).apply {
            // 约束要以父容器为锚点，所以必须给根布局一个 id
            id = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // ---------- 2. 国旗 ImageView：300dp × 200dp，fitCenter ----------
        val ivFlag = ImageView(this).apply {
            id = View.generateViewId()
            scaleType = ImageView.ScaleType.FIT_CENTER
            // R.drawable.flag 会按系统语言自动命中：
            // drawable/flag.jpg（美国）或 drawable-zh-rCN/flag.jpg（中国）或 drawable-es-rES/flag.jpg（西班牙）
            setImageResource(R.drawable.flag)
            layoutParams = ConstraintLayout.LayoutParams(dp(300), dp(200))
        }
        root.addView(ivFlag)

        // ---------- 3. 提示文字 TextView：30sp、加粗、屏幕正中 ----------
        tvMsg = TextView(this).apply {
            id = View.generateViewId()
            text = getString(R.string.tip_text)   // Hello World / 你好，世界! / Hola Mundo
            textSize = 30f                        // 单位默认就是 sp，等价于 android:textSize="30sp"
            typeface = Typeface.DEFAULT_BOLD      // 等价于 android:textStyle="bold"
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
        root.addView(tvMsg)

        // ---------- 4. 按钮 Button：20sp ----------
        val btnClick = Button(this).apply {
            id = View.generateViewId()
            text = getString(R.string.btn_text)   // Click Me / 点击我! / Clic aquí
            textSize = 20f                        // 等价于 android:textSize="20sp"
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
        root.addView(btnClick)

        // ---------- 5. 用 ConstraintSet 加约束，等价于布局文件里的 app:layout_constraintXxx ----------
        val cs = ConstraintSet()
        cs.clone(root)   // 克隆当前状态（三个子 View 此时都已经有 id）

        // 国旗：水平居中，距顶部 60dp
        cs.connect(ivFlag.id, ConstraintSet.TOP, root.id, ConstraintSet.TOP, dp(60))
        cs.connect(ivFlag.id, ConstraintSet.START, root.id, ConstraintSet.START, 0)
        cs.connect(ivFlag.id, ConstraintSet.END, root.id, ConstraintSet.END, 0)

        // 文字：上下左右都贴住父容器 —— 居中显示
        cs.connect(tvMsg.id, ConstraintSet.TOP, root.id, ConstraintSet.TOP, 0)
        cs.connect(tvMsg.id, ConstraintSet.BOTTOM, root.id, ConstraintSet.BOTTOM, 0)
        cs.connect(tvMsg.id, ConstraintSet.START, root.id, ConstraintSet.START, 0)
        cs.connect(tvMsg.id, ConstraintSet.END, root.id, ConstraintSet.END, 0)

        // 按钮：水平居中，距底部 140dp
        cs.connect(btnClick.id, ConstraintSet.BOTTOM, root.id, ConstraintSet.BOTTOM, dp(140))
        cs.connect(btnClick.id, ConstraintSet.START, root.id, ConstraintSet.START, 0)
        cs.connect(btnClick.id, ConstraintSet.END, root.id, ConstraintSet.END, 0)

        cs.applyTo(root)

        // ---------- 6. 交互：点击按钮换文字（文字同样取自字符串资源，会随系统语言切换） ----------
        btnClick.setOnClickListener {
            tvMsg.text = getString(R.string.click_msg)   // I was clicked / 我被点击了! / Fui pulsado
        }

        // ---------- 7. 纯代码构建好的视图直接交给 Activity 显示 ----------
        setContentView(root)
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density + 0.5f).toInt()
}
