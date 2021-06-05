package com.sardari.calculatedimens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.sardari.calculatedimens.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        calcDensity()

        binding.etLDPI.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!binding.etLDPI.hasFocus()) return

                mdpiValueChange(s.toString(), 1.333)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etMDPI.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    reset()
                    return
                }

                calcDimens()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etHDPI.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!binding.etHDPI.hasFocus()) return

                mdpiValueChange(s.toString(), 1.5)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etXHDPI.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!binding.etXHDPI.hasFocus()) return

                mdpiValueChange(s.toString(), 2.0)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etXXHDPI.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!binding.etXXHDPI.hasFocus()) return

                mdpiValueChange(s.toString(), 3.0)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.etXXXHDPI.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!binding.etXXXHDPI.hasFocus()) return

                mdpiValueChange(s.toString(), 4.0)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun calcDensity() {
        val metrics = resources.displayMetrics
        val densityDpi = (metrics.density * 160f)

        val densityClass = when (metrics.densityDpi) {
            DisplayMetrics.DENSITY_LOW -> "LDPI"
            DisplayMetrics.DENSITY_MEDIUM -> "MDPI"
            DisplayMetrics.DENSITY_HIGH -> "HDPI"
            DisplayMetrics.DENSITY_XHIGH -> "XHDPI"
            DisplayMetrics.DENSITY_XXHIGH -> "XXHDPI"
            DisplayMetrics.DENSITY_XXXHIGH -> "XXXHDPI"
            else -> "UNKNOWN"
        }

        binding.tvDensityClass.text = densityClass
        binding.tvDensityDpi.text = densityDpi.toString()
    }

    private fun mdpiValueChange(value: String, ratio: Double) {
        try {
            if (value.isEmpty()) {
                binding.etMDPI.setText("")
                return
            }

            val result = if (ratio < 1.5)
                ceil(value.toDouble() * ratio)
            else
                ceil(value.toDouble() / ratio)

            binding.etMDPI.setText(result.toInt().toString())
        } catch (e: Exception) {
            reset()
        }
    }

    private fun calcDimens() {
        try {
            if (binding.etMDPI.text.isNullOrEmpty())
                return

            if (!binding.etLDPI.hasFocus()) {
                binding.etLDPI.setText(
                    ceil(
                        binding.etMDPI.text.toString().toDouble() * 0.75
                    ).toInt().toString()
                )
            }

            if (!binding.etHDPI.hasFocus()) {
                binding.etHDPI.setText(
                    ceil(
                        binding.etMDPI.text.toString().toDouble() * 1.5
                    ).toInt().toString()
                )
            }

            if (!binding.etXHDPI.hasFocus()) {
                binding.etXHDPI.setText(
                    ceil(
                        binding.etMDPI.text.toString().toDouble() * 2
                    ).toInt().toString()
                )
            }

            if (!binding.etXXHDPI.hasFocus()) {
                binding.etXXHDPI.setText(
                    ceil(
                        binding.etMDPI.text.toString().toDouble() * 3
                    ).toInt().toString()
                )
            }

            if (!binding.etXXXHDPI.hasFocus()) {
                binding.etXXXHDPI.setText(
                    ceil(
                        binding.etMDPI.text.toString().toDouble() * 4
                    ).toInt().toString()
                )
            }
        } catch (ex: Exception) {
            reset()
        }
    }

    private fun reset() {
        if (!binding.etLDPI.hasFocus()) binding.etLDPI.setText("")
        if (!binding.etHDPI.hasFocus()) binding.etHDPI.setText("")
        if (!binding.etXHDPI.hasFocus()) binding.etXHDPI.setText("")
        if (!binding.etXXHDPI.hasFocus()) binding.etXXHDPI.setText("")
        if (!binding.etXXXHDPI.hasFocus()) binding.etXXXHDPI.setText("")
    }
}