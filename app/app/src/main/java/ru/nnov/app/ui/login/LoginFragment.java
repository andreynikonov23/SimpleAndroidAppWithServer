package ru.nnov.app.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import ru.nnov.app.R;
import ru.nnov.app.databinding.FragmentLoginBinding;
import ru.nnov.app.dto.AuthRequestDTO;
import ru.nnov.app.service.AuthProvider;
import ru.nnov.app.ui.login.LoginViewModel;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LoginViewModel loginViewModel =
                new ViewModelProvider(this).get(LoginViewModel.class);

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextInputEditText loginInput = root.findViewById(R.id.loginInput);
        TextInputEditText passwordInput = root.findViewById(R.id.passwordInput);
        Button login = root.findViewById(R.id.loginButton);

        login.setOnClickListener(v -> {
            //AuthRequestDTO authRequestDTO = new AuthRequestDTO(loginInput.getText().toString(), passwordInput.getText().toString());
            AuthProvider.test();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}