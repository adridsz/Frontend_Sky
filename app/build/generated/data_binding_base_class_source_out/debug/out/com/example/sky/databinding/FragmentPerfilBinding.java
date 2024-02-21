// Generated by view binder compiler. Do not edit!
package com.example.sky.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sky.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentPerfilBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView biografiaPerfil;

  @NonNull
  public final TextView cartelCarpetas;

  @NonNull
  public final TextView cartelImagenes;

  @NonNull
  public final ImageView iconoModificarCarpetas;

  @NonNull
  public final ImageView iconoModificarImagenes;

  @NonNull
  public final ImageView imagenPerfil;

  @NonNull
  public final FloatingActionButton lapiz;

  @NonNull
  public final TextView nombrePerfil;

  @NonNull
  public final RecyclerView recyclerPerfil;

  private FragmentPerfilBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView biografiaPerfil, @NonNull TextView cartelCarpetas,
      @NonNull TextView cartelImagenes, @NonNull ImageView iconoModificarCarpetas,
      @NonNull ImageView iconoModificarImagenes, @NonNull ImageView imagenPerfil,
      @NonNull FloatingActionButton lapiz, @NonNull TextView nombrePerfil,
      @NonNull RecyclerView recyclerPerfil) {
    this.rootView = rootView;
    this.biografiaPerfil = biografiaPerfil;
    this.cartelCarpetas = cartelCarpetas;
    this.cartelImagenes = cartelImagenes;
    this.iconoModificarCarpetas = iconoModificarCarpetas;
    this.iconoModificarImagenes = iconoModificarImagenes;
    this.imagenPerfil = imagenPerfil;
    this.lapiz = lapiz;
    this.nombrePerfil = nombrePerfil;
    this.recyclerPerfil = recyclerPerfil;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentPerfilBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentPerfilBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_perfil, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentPerfilBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.biografia_perfil;
      TextView biografiaPerfil = ViewBindings.findChildViewById(rootView, id);
      if (biografiaPerfil == null) {
        break missingId;
      }

      id = R.id.cartel_carpetas;
      TextView cartelCarpetas = ViewBindings.findChildViewById(rootView, id);
      if (cartelCarpetas == null) {
        break missingId;
      }

      id = R.id.cartel_imagenes;
      TextView cartelImagenes = ViewBindings.findChildViewById(rootView, id);
      if (cartelImagenes == null) {
        break missingId;
      }

      id = R.id.icono_modificar_carpetas;
      ImageView iconoModificarCarpetas = ViewBindings.findChildViewById(rootView, id);
      if (iconoModificarCarpetas == null) {
        break missingId;
      }

      id = R.id.icono_modificar_imagenes;
      ImageView iconoModificarImagenes = ViewBindings.findChildViewById(rootView, id);
      if (iconoModificarImagenes == null) {
        break missingId;
      }

      id = R.id.imagen_perfil;
      ImageView imagenPerfil = ViewBindings.findChildViewById(rootView, id);
      if (imagenPerfil == null) {
        break missingId;
      }

      id = R.id.lapiz;
      FloatingActionButton lapiz = ViewBindings.findChildViewById(rootView, id);
      if (lapiz == null) {
        break missingId;
      }

      id = R.id.nombre_perfil;
      TextView nombrePerfil = ViewBindings.findChildViewById(rootView, id);
      if (nombrePerfil == null) {
        break missingId;
      }

      id = R.id.recycler_perfil;
      RecyclerView recyclerPerfil = ViewBindings.findChildViewById(rootView, id);
      if (recyclerPerfil == null) {
        break missingId;
      }

      return new FragmentPerfilBinding((ConstraintLayout) rootView, biografiaPerfil, cartelCarpetas,
          cartelImagenes, iconoModificarCarpetas, iconoModificarImagenes, imagenPerfil, lapiz,
          nombrePerfil, recyclerPerfil);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
