import re

# Mapping of problematic character sequences to proper Unicode characters
char_mapping = {
    'ΓÇó': '•',
    'ΓÇ£': '"',
    'ΓÇ¥': '"',
    'ΓÇô': "'",
    'ΓÇÖ': "'",
    'ΓÇö': '-',
    'ΓÇÉ': '–',
    'ΓÇï': '—',
    'ΓÜá': '⚠️',
    'Γ?î': '❌',
    'Γä╣': 'ℹ️',
    '∩╕?': '',
    'Γ?º': '✅',
    'Γòá': '✔️',
    '∩╜«': '»',
    'ΓÜáΓÖ¢': '⚠️',
    'Γ?î ': '❌ ',
    'Γä╣ΓÖ¢': 'ℹ️ '
}

def clean_file(filename):
    with open(filename, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Replace all problematic sequences
    for bad_char, good_char in char_mapping.items():
        content = content.replace(bad_char, good_char)
    
    # Also replace any remaining problematic characters with a generic replacement
    # This will catch any we missed in the mapping
    content = re.sub(r'[^\x00-\x7F]+', '', content)  # Remove any non-ASCII characters
    
    # Write the cleaned content back to the file
    with open(filename, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"Cleaned {filename}")

if __name__ == "__main__":
    clean_file("src/main/java/Egalvanic.java")