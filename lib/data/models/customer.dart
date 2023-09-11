import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:kimai/data/models/models.dart';

part '../../generated/data/models/customer.freezed.dart';
part '../../generated/data/models/customer.g.dart';

@freezed
class Customer with _$Customer {
  const factory Customer({
    required int id,
    required String name,
    required String number,
    String? comment,
    required bool visible,
    required bool billable,
    required List<MetaField> metaFields,
    required List<Team> teams,
    String? color,
  }) = _Customer;

  factory Customer.fromJson(Map<String, dynamic> json) =>
      _$CustomerFromJson(json);
}
