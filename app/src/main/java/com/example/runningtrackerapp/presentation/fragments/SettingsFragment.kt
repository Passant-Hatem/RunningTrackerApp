package com.example.runningtrackerapp.presentation.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.runningtrackerapp.databinding.FragmentSettingsBinding
import com.example.runningtrackerapp.domain.model.UserInfo
import com.example.runningtrackerapp.domain.model.UserInfoState
import com.example.runningtrackerapp.util.Constants.KEY_HEIGHT
import com.example.runningtrackerapp.util.Constants.KEY_NAME
import com.example.runningtrackerapp.util.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldsFromSharedPref()
        binding.updateBttn.setOnClickListener {
            val userInfo = validatesUserInfo()
            when (userInfo.state) {
                UserInfoState.Success -> {
                    Snackbar.make(view, "Saved changes", Snackbar.LENGTH_LONG).show()
                    updatePersonalDataToSharedPref(userInfo)
                }
                UserInfoState.EmptyFields -> Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
                UserInfoState.WrongInfo -> Snackbar.make(requireView(), "Please enter correct data", Snackbar.LENGTH_SHORT).show()
            }
        }


    }

    private fun loadFieldsFromSharedPref() {
        val name = sharedPreferences.getString(KEY_NAME, "")
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 80f)
        val height = sharedPreferences.getFloat(KEY_HEIGHT, 170f)
        binding.etName.setText(name)
        binding.etWeight.setText(weight.toString())
        binding.etHeight.setText(height.toString())
    }

    private fun validatesUserInfo(): UserInfo {
        var userInfo = UserInfo()

        if(binding.etName.text.toString().isEmpty()
            || binding.etWeight.text.toString().isEmpty()
            || binding.etHeight.text.toString().isEmpty()) {

            return userInfo
        }

        val name = binding.etName.text.toString()
        val weight = binding.etWeight.text.toString().toFloat()
        val height = binding.etHeight.text.toString().toFloat()

        if (weight < 45 || weight > 250 || height < 140 || height > 220){
            userInfo.state = UserInfoState.WrongInfo
            return userInfo
        }

        userInfo = UserInfo(
            name, weight, height, UserInfoState.Success
        )

        return userInfo
    }

    private fun updatePersonalDataToSharedPref(userInfo: UserInfo) = with(userInfo){
        sharedPreferences.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weight)
            .putFloat(KEY_HEIGHT, height)
            .apply()
    }
}