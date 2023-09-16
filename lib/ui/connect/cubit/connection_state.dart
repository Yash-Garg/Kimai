part of 'connection_cubit.dart';

class ConnState extends Equatable {
  final String baseURL;
  final FieldStatus baseURLStatus;
  final String username;
  final FieldStatus usernameStatus;
  final String apiKey;
  final FieldStatus apiKeyStatus;
  final ScreenStatus status;
  final String? error;

  const ConnState({
    required this.baseURL,
    required this.username,
    required this.apiKey,
    required this.baseURLStatus,
    required this.usernameStatus,
    required this.apiKeyStatus,
    required this.status,
    this.error,
  });

  factory ConnState.initial() {
    return const ConnState(
      baseURL: '',
      username: '',
      apiKey: '',
      baseURLStatus: FieldStatus.initial,
      usernameStatus: FieldStatus.initial,
      apiKeyStatus: FieldStatus.initial,
      status: ScreenStatus.initial,
    );
  }

  ConnState copyWith({
    String? baseURL,
    FieldStatus? baseURLStatus,
    String? username,
    FieldStatus? usernameStatus,
    String? apiKey,
    FieldStatus? apiKeyStatus,
    ScreenStatus? status,
    String? error,
  }) {
    return ConnState(
      baseURL: baseURL ?? this.baseURL,
      baseURLStatus: baseURLStatus ?? this.baseURLStatus,
      username: username ?? this.username,
      usernameStatus: usernameStatus ?? this.usernameStatus,
      apiKey: apiKey ?? this.apiKey,
      apiKeyStatus: apiKeyStatus ?? this.apiKeyStatus,
      status: status ?? this.status,
      error: error,
    );
  }

  @override
  List<Object?> get props => [
        baseURL,
        username,
        apiKey,
        baseURLStatus,
        usernameStatus,
        apiKeyStatus,
        status,
        error,
      ];
}
