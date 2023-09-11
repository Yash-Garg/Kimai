import 'package:freezed_annotation/freezed_annotation.dart';

part '../../generated/data/models/ping.freezed.dart';
part '../../generated/data/models/ping.g.dart';

@freezed
class Ping with _$Ping {
  const factory Ping({
    required String message,
  }) = _Ping;

  factory Ping.fromJson(Map<String, dynamic> json) => _$PingFromJson(json);
}
