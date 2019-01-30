import React from 'react';
import Typography from '@material-ui/core/Typography';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import AccessTimeIcon from '@material-ui/icons/AccessTime';
import SchoolIcon from '@material-ui/icons/School';
import { format } from 'date-fns';

function ApplicationSummary({ competences, periods }) {

    return (
        <div>
            <Typography variant="h3" gutterBottom>Application Summary</Typography>
            <Typography variant="h5">Areas of expertise</Typography>
            <List>
                {
                    competences.map(competence => (
                        <ListItem>
                            <ListItemIcon>
                                <SchoolIcon />
                            </ListItemIcon>
                            <ListItemText primary={competence.name} secondary={`${competence.years} years of experience`} />
                        </ListItem>
                    ))
                }
            </List>
            <Typography variant="h5">Availibility</Typography>
            <List>
                {
                    periods.map(period => {
                        const fromString = format(period.from, 'yyyy-MM-dd');
                        const toString = format(period.to, 'yyyy-MM-dd');

                        return (
                            <ListItem>
                                <ListItemIcon>
                                    <AccessTimeIcon />
                                </ListItemIcon>
                                <ListItemText
                                    primary={`${fromString} - ${toString}`}
                                />
                            </ListItem>
                        );
                    })
                }
            </List>
        </div>
    );
}

export default ApplicationSummary;