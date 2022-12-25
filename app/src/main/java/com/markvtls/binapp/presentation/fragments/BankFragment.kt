package com.markvtls.binapp.presentation.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.markvtls.binapp.R
import com.markvtls.binapp.databinding.FragmentBankBinding
import com.markvtls.binapp.domain.model.BinInfo
import com.markvtls.binapp.presentation.BankViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BankFragment : Fragment() {


    private var _binding: FragmentBankBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BankViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            searchField.setOnClickListener {
                searchField.showDropDown()
            }

            searchField.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val inputMethodManager = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(textView.windowToken, 0)
                    getBinInfo()
                }
                false
            }

        }

        viewModel.apply {
            binHistory.observe(viewLifecycleOwner) {
                loadBinHistory(it)
            }

            binInfo.observe(viewLifecycleOwner) {
                showResponseInfo(it)
                openMap(it.latitude, it.longitude)
            }
        }

    }

    private fun getBinInfo() {
        if (checkInput()) {
            val bin = binding.searchField.text.toString()
            viewModel.apply {
                getInfo(bin)
                saveToHistory(bin)
            }

        }
    }

    private fun loadBinHistory(history: List<String>) {
        val adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,history )
        binding.searchField.setAdapter(adapter)
    }

    private fun checkInput(): Boolean {
        val input = binding.searchField
        var conditions = 0

        if (input.text.isNullOrBlank()) {
            input.error = "Заполните поле"
            conditions++
        }

        if (input.text.length < 4) {
            input.error = "Необходимо ввести минимум 4 цифры"
            conditions++
        }

        return conditions == 0

    }

    private fun showResponseInfo(binInfo: BinInfo) {
        binding.apply {
            lengthText.text = binInfo.length
            luhnText.text = binInfo.luhn
            typeText.text = binInfo.type.uppercase()
            brandText.text = binInfo.brand
            schemeText.text = binInfo.scheme.uppercase()
            prepaidText.text = binInfo.prepaid
            bankNameText.text = binInfo.name
            bankUrlText.text = binInfo.url
            bankPhoneText.text = binInfo.phone
            countryEmoji.text = binInfo.emoji
            countryNameText.text = binInfo.countryName
            countryCoordsText.text = getString(R.string.coords,binInfo.latitude, binInfo.longitude)
        }

    }

    private fun openMap(latitude: String, longitude: String) {
        if (latitude != "?") {
            binding.countryCoordsText.setOnClickListener {
                val coordinates = Uri.parse("geo:$latitude,$longitude")
                val openMapIntent = Intent(Intent.ACTION_VIEW, coordinates)
                openMapIntent.setPackage("com.google.android.apps.maps")
                startActivity(openMapIntent)
            }
        } else binding.countryCoordsText.setOnClickListener {  }
    }


}