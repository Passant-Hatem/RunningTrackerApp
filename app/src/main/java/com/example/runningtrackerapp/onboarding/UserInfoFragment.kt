package com.example.runningtrackerapp.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.runningtrackerapp.databinding.FragmentUserInfoBinding
import com.example.runningtrackerapp.presentation.MainActivity
import com.example.runningtrackerapp.util.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserInfoFragment : Fragment() {

    private lateinit var binding: FragmentUserInfoBinding

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false)

        binding.finishBttn.setOnClickListener {
            val userInfo = validatesUserInfo()
            when (userInfo.state) {
                UserInfoState.Success -> {
                    writePersonalDataToSharedPref(userInfo)
                    finishBoarding()
                }
                UserInfoState.EmptyFields -> Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
                UserInfoState.WrongInfo -> Snackbar.make(requireView(), "Please enter correct data", Snackbar.LENGTH_SHORT).show()
            }
        }

        return binding.root
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

    private fun writePersonalDataToSharedPref(userInfo: UserInfo) = with(userInfo){
        sharedPref.edit()
            .putString(Constants.KEY_NAME, name)
            .putFloat(Constants.KEY_WEIGHT, weight)
            .putFloat(Constants.KEY_HEIGHT, height)
            .putBoolean(Constants.KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        //todo
        val toolbarText = "Let's go, $name!"
        requireActivity().actionBar?.title = toolbarText
    }

    private fun finishBoarding(){
        startActivity(Intent(activity, MainActivity::class.java))
        onBoardingFinished()
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}

data class UserInfo(
    val name: String = "",
    val weight: Float = 0f,
    val height: Float = 0f,
    var state: UserInfoState = UserInfoState.EmptyFields
)

enum class UserInfoState{
    Success,
    EmptyFields,
    WrongInfo
}