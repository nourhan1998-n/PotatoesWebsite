package org.example.exceptions.notfound;

public class CategoryNotFoundException extends NotFoundException {
  public CategoryNotFoundException(String message) {
    super(message);
  }
}