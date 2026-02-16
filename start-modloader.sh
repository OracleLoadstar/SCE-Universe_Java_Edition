#!/usr/bin/env bash
script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
settings_file="$script_dir/settings.xml"
if [[ -f "$settings_file" ]]; then
	tmp_file="$settings_file.tmp"
	awk '
	BEGIN{replaced=0}
	{
		if (!replaced && $0 ~ /<string>mod<\/string>/) {
			print; getline;
			if ($0 ~ /<boolean>/) {
				sub(/<boolean>[^<]*<\/boolean>/,"<boolean>true</boolean>");
				print; replaced=1; next;
			}
		}
		print;
	}
	END{}
' "$settings_file" > "$tmp_file" && mv "$tmp_file" "$settings_file"
fi
jar_file="$script_dir/SCE-Universe.jar"
if [[ ! -f "$jar_file" ]]; then
	jar_file="$script_dir/app.jar"
fi
if [[ ! -f "$jar_file" ]]; then
	echo "Jar not found: SCE-Universe.jar or app.jar" >&2
	exit 1
fi
java -jar "$jar_file" -mod 2> log.log
