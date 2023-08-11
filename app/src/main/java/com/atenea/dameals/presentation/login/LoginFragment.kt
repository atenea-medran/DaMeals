package com.atenea.dameals.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.atenea.dameals.databinding.FragmentLoginBinding
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.DaMealsTheme

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DaMealsTheme {
                    LoginScreen(
                        onLoginSuccess = { Toast.makeText(context, "Login exitoso",Toast.LENGTH_SHORT).show() },
                        onLoginFail = { Toast.makeText(context, "LOGIN FALLIDO",Toast.LENGTH_SHORT).show() }
                    )

                }
            }
        }
        return view
    }
}