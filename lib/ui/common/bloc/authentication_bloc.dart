import 'dart:async';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:injectable/injectable.dart';
import 'package:kimai/data/app_db.dart';
import 'package:kimai/data/models/auth_user.dart';
import 'package:kimai/data/schema/instance.dart';

import 'authentication_state.dart';

part 'authentication_event.dart';

@lazySingleton
class AuthenticationBloc
    extends Bloc<AuthenticationEvent, AuthenticationState> {
  final AppDatabase _db;

  AuthenticationBloc({
    required AppDatabase db,
  })  : _db = db,
        super(const AuthenticationState.initial()) {
    on<AuthInitialized>(_onAuthInitialized);
    on<AuthenticationUserUpdated>(_onAuthenticationUserUpdated);
    on<AuthenticationLogoutRequested>(_onAuthenticationLogoutRequested);
  }

  FutureOr<void> _onAuthInitialized(
    AuthInitialized event,
    Emitter<AuthenticationState> emit,
  ) async {
    emit(const AuthenticationState.loading());

    final instance = await _db.getInstance();

    if (instance == null) {
      emit(const AuthenticationState.unauthenticated());
    } else {
      final user = AuthUser(
        baseUrl: instance.baseURL,
        username: instance.username,
        token: instance.apiKey,
      );

      emit(AuthenticationState.authenticated(user: user));
    }
  }

  FutureOr<void> _onAuthenticationUserUpdated(
    AuthenticationUserUpdated event,
    Emitter<AuthenticationState> emit,
  ) async {
    final instance = Instance()
      ..apiKey = event.user.token
      ..baseURL = event.user.baseUrl
      ..username = event.user.username;

    await _db.saveInstance(instance: instance);
    emit(AuthenticationState.authenticated(user: event.user));
  }

  FutureOr<void> _onAuthenticationLogoutRequested(
    AuthenticationLogoutRequested event,
    Emitter<AuthenticationState> emit,
  ) async {}
}
