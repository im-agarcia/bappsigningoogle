package com.example.smoketest.fragments.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.smoketest.R
import com.example.smoketest.databinding.FragmentMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar


class MainFragment : Fragment() {

    //Google signin init
    private lateinit var googleSignInOptions : GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient

    //firebase passthrow
    private lateinit var auth: FirebaseAuth

    private lateinit var binding : FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser!=null){
            findNavController().navigate(R.id.action_mainFragment_to_menuActivity)
        }

        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail().build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions)

        binding.btnLoginGmail.setOnClickListener{
            //si quiero poner una barra de progreso puedo poner un binding.objeto.visibility = View.VISIBLE
            signIn()
        }
        binding.btnLoginBandapp.setOnClickListener{
        }

        binding.forgotPassword.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_pwdResetFragment)
        }
    }



    private fun signIn() {
        val intent = googleSignInClient.signInIntent
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== REQUEST_CODE){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        try{
            val account = task!!.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
        }
        catch(e: ApiException){
            Snackbar.make(binding.mainFragmentLayout,"Failed to Login", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.id, null)
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity() ){ task ->
            if(task.isSuccessful){
                findNavController().navigate(R.id.action_mainFragment_to_menuActivity)
            }

        }
    }

    companion object {
        private const val REQUEST_CODE = 4
    }


}