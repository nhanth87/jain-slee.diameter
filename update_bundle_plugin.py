import os
import re

# Bundle plugin XML to add
bundle_plugin_xml = '''\n\t\t\t<plugin>
\t\t\t\t<groupId>org.apache.felix</groupId>
\t\t\t\t<artifactId>maven-bundle-plugin</artifactId>
\t\t\t\t<version>5.1.9</version>
\t\t\t\t<executions>
\t\t\t\t\t<execution>
\t\t\t\t\t\t<id>bundle-manifest</id>
\t\t\t\t\t\t<phase>process-classes</phase>
\t\t\t\t\t\t<goals>
\t\t\t\t\t\t\t<goal>manifest</goal>
\t\t\t\t\t\t</goals>
\t\t\t\t\t</execution>
\t\t\t\t</executions>
\t\t\t</plugin>'''

base_dir = r'C:\Users\Windows\Desktop\ethiopia-working-dir\jain-slee.diameter'
resources_dir = os.path.join(base_dir, 'resources')

# Find all pom.xml files in resources
pom_files = []
for root, dirs, files in os.walk(resources_dir):
    if 'pom.xml' in files:
        pom_files.append(os.path.join(root, 'pom.xml'))

# Also add enablers
enablers_dir = os.path.join(base_dir, 'enablers')
if os.path.exists(enablers_dir):
    for root, dirs, files in os.walk(enablers_dir):
        if 'pom.xml' in files:
            pom_files.append(os.path.join(root, 'pom.xml'))

print(f"Found {len(pom_files)} pom.xml files")

# Process each pom.xml
updated = 0
skipped = 0
for pom_file in pom_files:
    try:
        with open(pom_file, 'r', encoding='utf-8') as f:
            content = f.read()
        
        # Check if it's a jar packaging (or no packaging which defaults to jar)
        has_jar_packaging = '<packaging>jar</packaging>' in content or '<packaging>bundle</packaging>' in content or '<packaging>war</packaging>' in content
        has_pom_packaging = '<packaging>pom</packaging>' in content
        
        # If no packaging specified, it defaults to jar
        if not has_pom_packaging and not has_jar_packaging:
            has_jar_packaging = True
        
        if has_jar_packaging:
            # Check if bundle plugin already exists
            if 'maven-bundle-plugin' not in content:
                # Find <plugins> section and add bundle plugin
                if '<plugins>' in content and '</plugins>' in content:
                    # Insert after <plugins>
                    idx = content.find('<plugins>') + len('<plugins>')
                    content = content[:idx] + bundle_plugin_xml + content[idx:]
                    with open(pom_file, 'w', encoding='utf-8') as f:
                        f.write(content)
                    updated += 1
                    print(f"Updated: {os.path.basename(os.path.dirname(pom_file))}/pom.xml")
                else:
                    skipped += 1
            else:
                skipped += 1
    except Exception as e:
        print(f"Error processing {pom_file}: {e}")

print(f"Updated {updated} files, skipped {skipped} files")
