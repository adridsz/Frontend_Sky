// Generated by view binder compiler. Do not edit!
package com.example.sky.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sky.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentOtrosPerfilesBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView ImageViewCarpeta;

  @NonNull
  public final TextView TextViewiCarpeta;

  @NonNull
  public final LinearLayout botonesLayout;

  @NonNull
  public final Button btnCarpetas;

  @NonNull
  public final Button btnImagenes;

  @NonNull
  public final TextView descripcionUsuario;

  @NonNull
  public final ImageView fotoPerfil;

  @NonNull
  public final TextView nombreUsuario;

  @NonNull
  public final LinearLayout otrosPerfilesFragment;

  @NonNull
  public final RecyclerView recyclerViewImagenes;

  private FragmentOtrosPerfilesBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView ImageViewCarpeta, @NonNull TextView TextViewiCarpeta,
      @NonNull LinearLayout botonesLayout, @NonNull Button btnCarpetas, @NonNull Button btnImagenes,
      @NonNull TextView descripcionUsuario, @NonNull ImageView fotoPerfil,
      @NonNull TextView nombreUsuario, @NonNull LinearLayout otrosPerfilesFragment,
      @NonNull RecyclerView recyclerViewImagenes) {
    this.rootView = rootView;
    this.ImageViewCarpeta = ImageViewCarpeta;
    this.TextViewiCarpeta = TextViewiCarpeta;
    this.botonesLayout = botonesLayout;
    this.btnCarpetas = btnCarpetas;
    this.btnImagenes = btnImagenes;
    this.descripcionUsuario = descripcionUsuario;
    this.fotoPerfil = fotoPerfil;
    this.nombreUsuario = nombreUsuario;
    this.otrosPerfilesFragment = otrosPerfilesFragment;
    this.recyclerViewImagenes = recyclerViewImagenes;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentOtrosPerfilesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentOtrosPerfilesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_otros_perfiles, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentOtrosPerfilesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ImageViewCarpeta;
      ImageView ImageViewCarpeta = ViewBindings.findChildViewById(rootView, id);
      if (ImageViewCarpeta == null) {
        break missingId;
      }

      id = R.id.TextViewiCarpeta;
      TextView TextViewiCarpeta = ViewBindings.findChildViewById(rootView, id);
      if (TextViewiCarpeta == null) {
        break missingId;
      }

      id = R.id.botonesLayout;
      LinearLayout botonesLayout = ViewBindings.findChildViewById(rootView, id);
      if (botonesLayout == null) {
        break missingId;
      }

      id = R.id.btnCarpetas;
      Button btnCarpetas = ViewBindings.findChildViewById(rootView, id);
      if (btnCarpetas == null) {
        break missingId;
      }

      id = R.id.btnImagenes;
      Button btnImagenes = ViewBindings.findChildViewById(rootView, id);
      if (btnImagenes == null) {
        break missingId;
      }

      id = R.id.descripcionUsuario;
      TextView descripcionUsuario = ViewBindings.findChildViewById(rootView, id);
      if (descripcionUsuario == null) {
        break missingId;
      }

      id = R.id.fotoPerfil;
      ImageView fotoPerfil = ViewBindings.findChildViewById(rootView, id);
      if (fotoPerfil == null) {
        break missingId;
      }

      id = R.id.nombreUsuario;
      TextView nombreUsuario = ViewBindings.findChildViewById(rootView, id);
      if (nombreUsuario == null) {
        break missingId;
      }

      LinearLayout otrosPerfilesFragment = (LinearLayout) rootView;

      id = R.id.recyclerViewImagenes;
      RecyclerView recyclerViewImagenes = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewImagenes == null) {
        break missingId;
      }

      return new FragmentOtrosPerfilesBinding((LinearLayout) rootView, ImageViewCarpeta,
          TextViewiCarpeta, botonesLayout, btnCarpetas, btnImagenes, descripcionUsuario, fotoPerfil,
          nombreUsuario, otrosPerfilesFragment, recyclerViewImagenes);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
