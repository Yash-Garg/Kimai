import 'package:flutter/material.dart';

class CustomTextField extends StatelessWidget {
  final TextEditingController? controller;
  final bool obscure, isReadOnly;
  final TextInputType? inputType;
  final Function(String?) validator;
  final Function(String)? onChanged;
  final String? hintText, labelText;
  final Widget? suffixIcon, prefixIcon, prefix, suffix;

  const CustomTextField({
    super.key,
    this.controller,
    required this.validator,
    this.onChanged,
    this.inputType,
    this.isReadOnly = false,
    this.obscure = false,
    this.hintText,
    this.labelText,
    this.suffixIcon,
    this.prefixIcon,
    this.prefix,
    this.suffix,
  });

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      style: TextStyle(fontSize: 16, letterSpacing: 1),
      controller: controller,
      textAlignVertical: TextAlignVertical.center,
      maxLines: 1,
      obscureText: obscure,
      readOnly: isReadOnly,
      autovalidateMode: AutovalidateMode.onUserInteraction,
      validator: (val) => validator(val),
      onChanged: onChanged,
      decoration: InputDecoration(
        hintText: hintText,
        labelText: labelText,
        border: OutlineInputBorder(),
        suffix: suffix,
        prefix: prefix,
        suffixIcon: suffixIcon,
        prefixIcon: prefixIcon,
      ),
      keyboardType: inputType,
    );
  }
}
