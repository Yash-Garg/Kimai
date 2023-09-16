import 'package:flutter/material.dart';

class CustomTextField extends StatelessWidget {
  final TextEditingController controller;
  final bool obscure, isReadOnly;
  final TextInputType? inputType;
  final Function(String?) validator;
  final String? hintText, labelText;
  final Widget? suffixIcon, prefixIcon;

  const CustomTextField({
    super.key,
    required this.controller,
    required this.validator,
    this.inputType,
    this.isReadOnly = false,
    this.obscure = false,
    this.hintText,
    this.labelText,
    this.suffixIcon,
    this.prefixIcon,
  });

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      style: TextStyle(fontSize: 16, letterSpacing: 1),
      controller: controller,
      maxLines: 1,
      obscureText: obscure,
      readOnly: isReadOnly,
      autovalidateMode: AutovalidateMode.onUserInteraction,
      validator: (val) => validator(val),
      decoration: InputDecoration(
        hintText: hintText,
        labelText: labelText,
        border: OutlineInputBorder(),
        suffixIcon: suffixIcon,
        prefixIcon: prefixIcon,
      ),
      keyboardType: inputType,
    );
  }
}
