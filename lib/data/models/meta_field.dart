import 'package:freezed_annotation/freezed_annotation.dart';

part '../../generated/data/models/meta_field.freezed.dart';
part '../../generated/data/models/meta_field.g.dart';

@freezed
class MetaField with _$MetaField {
  const factory MetaField({
    required String name,
    required String value,
  }) = _MetaField;

  factory MetaField.fromJson(Map<String, dynamic> json) =>
      _$MetaFieldFromJson(json);
}
